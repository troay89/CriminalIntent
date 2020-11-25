package com.bignerdranch.android.criminalintent

import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import java.io.File

private const val ARG_IMAGE = "date"

class ImageFragment: DialogFragment(){

    private lateinit var detailedPhoto: ImageView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val urlStringImage = arguments?.getSerializable(ARG_IMAGE) as File
        val dialogDetailedPhoto = Dialog(requireContext())
        dialogDetailedPhoto.setContentView(R.layout.fragment_image)
        detailedPhoto = dialogDetailedPhoto.findViewById(R.id.detailed_photo)  as ImageView
        val bitmap = getScaledBitmap(urlStringImage.path, requireActivity())
        detailedPhoto.setImageBitmap(bitmap)
        return dialogDetailedPhoto
    }

    companion object {
        fun newInstance(image: File): ImageFragment {
            val args = Bundle().apply {
                putSerializable(ARG_IMAGE, image)
            }
            return ImageFragment().apply {
                arguments = args
            }
        }
    }
}