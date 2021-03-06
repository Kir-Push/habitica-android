package com.habitrpg.android.habitica.ui.fragments.setup

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.habitrpg.android.habitica.R
import com.habitrpg.android.habitica.components.AppComponent
import com.habitrpg.android.habitica.extensions.inflate
import com.habitrpg.android.habitica.extensions.notNull
import com.habitrpg.android.habitica.ui.fragments.BaseFragment
import com.habitrpg.android.habitica.ui.helpers.bindView
import com.habitrpg.android.habitica.ui.helpers.resetViews

class IntroFragment : BaseFragment() {

    private val subtitleTextView: TextView? by bindView(R.id.subtitleTextView)
    private val titleTextView: TextView? by bindView(R.id.titleTextView)
    private val titleImageView: ImageView? by bindView(R.id.titleImageView)
    private val descriptionTextView: TextView? by bindView(R.id.descriptionTextView)
    private val imageView: ImageView? by bindView(R.id.imageView)
    private val containerView: ViewGroup? by bindView(R.id.container_view)

    private var image: Drawable? = null
    private var titleImage: Drawable? = null
    private var subtitle: String? = null
    private var title: String? = null
    private var description: String? = null
    private var backgroundColor: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return container?.inflate(R.layout.fragment_intro)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resetViews()

        if (this.image != null) {
            this.imageView?.setImageDrawable(this.image)
        }

        if (this.titleImage != null) {
            this.titleImageView?.setImageDrawable(this.titleImage)
        }

        if (this.subtitle != null) {
            this.subtitleTextView?.text = this.subtitle
        }

        if (this.title != null) {
            this.titleTextView?.text = this.title
        }

        if (this.description != null) {
            this.descriptionTextView?.text = this.description
        }

        backgroundColor.notNull {
            this.containerView?.setBackgroundColor(it)
        }
    }

    override fun injectFragment(component: AppComponent) {
        component.inject(this)
    }

    fun setImage(image: Drawable?) {
        this.image = image
        if (image != null) {
            this.imageView?.setImageDrawable(image)
        }
    }

    fun setTitleImage(image: Drawable?) {
        this.titleImage = image
        this.titleImageView?.setImageDrawable(image)
    }

    fun setSubtitle(text: String?) {
        this.subtitle = text
        subtitleTextView?.text = text
    }

    fun setTitle(text: String?) {
        this.title = text
        titleTextView?.text = text
    }

    fun setDescription(text: String?) {
        descriptionTextView?.text = text
    }

    fun setBackgroundColor(color: Int) {
        this.backgroundColor = color
        containerView?.setBackgroundColor(color)
    }

}
