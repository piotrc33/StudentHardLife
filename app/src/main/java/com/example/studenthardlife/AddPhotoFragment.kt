package com.example.studenthardlife

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.studenthardlife.databinding.FragmentAddPhotoBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

class AddPhotoFragment : Fragment() {

    private lateinit var binding: FragmentAddPhotoBinding
    private lateinit var pictureAbsolutePath: Uri

    private val problemListsViewModel: ProblemListsViewModel by viewModels()


    private lateinit var listName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            listName = it.getString("listName").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddPhotoBinding.inflate(inflater, container, false)

        binding.photoButton.setOnClickListener {
            openCamera()
        }

        binding.saveButton.setOnClickListener {
            if (checkForErrors())
                Toast.makeText(
                    context,
                    "Action failed",
                    Toast.LENGTH_LONG
                ).show()
            else {
                // creating updated problemList with proper image path
                val updatedProblemList =
                    ProblemList(listName, pictureAbsolutePath.toString())
                problemListsViewModel.updateList(updatedProblemList)
            }
            findNavController().navigate(AddPhotoFragmentDirections.actionAddPhotoFragmentToProblemListsFragment())
        }

        return binding.root
    }

    private val requestCameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            launchCamera()
        }
    }

    private val resultLauncherCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.imageViewPicture.setImageBitmap(imageBitmap)
            pictureAbsolutePath = saveImage(imageBitmap)
        }
    }

    private fun openCamera() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.CAMERA
            ) ==
                    PackageManager.PERMISSION_GRANTED -> {
                launchCamera() // włączam aplikację przez implicit intent
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.CAMERA
            ) -> {
                showMessageOKCancel(getString(R.string.rationale_camera)) // Rationale
            }
            else -> {
                requestCameraPermissionLauncher
                    .launch(Manifest.permission.CAMERA) // jeżeli nie to nic nie robię
            }
        }
    }


    private fun showMessageOKCancel(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setPositiveButton("OK") { dialogInterface: DialogInterface, _: Int ->
                requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                dialogInterface.dismiss()
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

    private fun launchCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncherCamera.launch(intent)
    }


    private fun checkForErrors(): Boolean {
//        if (binding.editPhotoName.text.isEmpty())
//            return true
        if (!this::pictureAbsolutePath.isInitialized)
            return true
        return false
    }

    private fun saveImage(bitmap: Bitmap): Uri {
        var file = requireContext().getDir("problemListPhotos", Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")

        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return Uri.parse(file.absolutePath)
    }

}