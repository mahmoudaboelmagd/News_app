package com.our.edu.newsapp.ui.fragments.detailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.our.edu.newsapp.dataModel.DomainNewsModel
import com.our.edu.newsapp.R
import com.our.edu.newsapp.databinding.FragmentDetailsBinding
import com.our.edu.newsapp.utils.Constants
import com.our.edu.newsapp.utils.IntentClass
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private lateinit var detailsBinding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        detailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        return detailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val domainApplicationModel = requireArguments().getSerializable(Constants.ARGUMENT_KEY) as DomainNewsModel

        Picasso.get().load(domainApplicationModel.urlToImage).placeholder(R.drawable.placeholder).
                into(detailsBinding.articleImg)

        detailsBinding.titleTxt.text = domainApplicationModel.title
        detailsBinding.authorTxt.text = getString(R.string.by)+" "+domainApplicationModel.author
        detailsBinding.publishedAtTxt.text = domainApplicationModel.publishedAt
        detailsBinding.descriptionTxt.text = domainApplicationModel.description


        detailsBinding.openWebsiteButton.setOnClickListener {
            IntentClass.goToLink(requireActivity(), domainApplicationModel.url!!)
        }
    }

}