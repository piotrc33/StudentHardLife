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

    // uri holding a path to added picture
    private lateinit var pictureAbsolutePath: Uri

    private val problemListsViewModel: ProblemListsViewModel by viewModels()

    // Name of list to which a photo is being added
    private lateinit var listName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setting navigation arguments
        arguments?.let {
            listName = it.getString("listName").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPhotoBinding.inflate(inflater, container, false)

        binding.photoButton.setOnClickListener {
            openCamera()
        }

        binding.saveButton.setOnClickListener {
            // showing toast on fail
            if (checkForErrors()) Toast.makeText(
                context, "Action failed", Toast.LENGTH_LONG
            ).show()
            else {
                // updating the list by adding picture path to id
                problemListsViewModel.updateList(
                    ProblemList(
                        listName, pictureAbsolutePath.toString()
                    )
                )
            }

            // navigating back
            findNavController().navigate(AddPhotoFragmentDirections.actionAddPhotoFragmentToProblemListsFragment())
        }

        return binding.root
    }

    // requsting for camera permission
    private val requestCameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            launchCamera()
        }
    }

    // camera intent starting value
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
            ) == PackageManager.PERMISSION_GRANTED -> {
                launchCamera() // if app has all permissions launchCamera
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(), Manifest.permission.CAMERA
            ) -> {
                showMessageOKCancel(getString(R.string.rationale_camera)) // Rationale
            }
            else -> {
                requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA) // jeżeli nie to nic nie robię
            }
        }
    }


    private fun showMessageOKCancel(message: String) {
        AlertDialog.Builder(requireContext()).setMessage(message)
            .setPositiveButton("OK") { dialogInterface: DialogInterface, _: Int ->
                requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                dialogInterface.dismiss()
            }.setNegativeButton("Cancel", null).create().show()
    }

    private fun launchCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncherCamera.launch(intent)
    }


    private fun checkForErrors(): Boolean {
        if (!this::pictureAbsolutePath.isInitialized) return true
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