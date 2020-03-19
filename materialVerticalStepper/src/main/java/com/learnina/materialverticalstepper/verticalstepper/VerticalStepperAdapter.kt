package com.learnina.materialverticalstepper.verticalstepper

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.learnina.materialverticalstepper.R
import com.learnina.materialverticalstepper.databinding.StepperItemBinding

class VerticalStepperAdapter(
    private val fragmentManager: FragmentManager,
    val context: Context
) : ListAdapter<VerticalItem, VerticalStepperAdapter.StepperViewHolder>(
    TestDiffCallback()
) {
    private var activeDrawable:Drawable = DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.circle_bg)!!)
    private var inActiveDrawable:Drawable = DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.circle_bg)!!);
    init {
        DrawableCompat.setTint(activeDrawable, ContextCompat.getColor(context, activeColor))
        DrawableCompat.setTint(inActiveDrawable, ContextCompat.getColor(context, inActiveColor))
    }
    private var lastClickedItemPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepperViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: StepperItemBinding = StepperItemBinding.inflate(inflater, parent, false)
        return StepperViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: StepperViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.item = item

        if (item.isOpen) {
            addFragmentToView(holder, position)
            addFragmentToView(holder, position)
        }

        if (item.isActive){
            holder.binding.circleImage.background = activeDrawable
        }else{
            holder.binding.circleImage.background = inActiveDrawable

        }

        if (position >= itemCount - 1){

            holder.binding.btnNext.text = finishText
            holder.binding.btnNext.setOnClickListener {
                item.fragment.finishButtonAction()
            }
        }else{
            holder.binding.btnNext.text = nextText

            holder.binding.btnNext.setOnClickListener {
                if (item.fragment.checkFieldsValidation()) {
                    if (position < itemCount - 1) {
                        item.isCompleted = true
                        val nextItem = getItem(position + 1)
                        nextItem.isOpen = true
                        nextItem.isActive = true
                        item.isOpen = false
                        notifyItemChanged(position)
                        notifyItemChanged(position + 1)
                        lastClickedItemPosition = position + 1

                    }
                }


            }

        }

        holder.binding.btnPrev.setOnClickListener {
            if (position > 0) {

                val prevItem = getItem(position - 1)
                prevItem.isOpen = true
                item.isOpen = false
                notifyItemChanged(position)
                notifyItemChanged(position - 1)
                lastClickedItemPosition = position - 1

            }
        }

        holder.binding.circleImage.setOnClickListener {
            if (item.isActive) {

                item.isOpen = !item.isOpen

                if (lastClickedItemPosition != -1 && getItem(lastClickedItemPosition).isOpen && lastClickedItemPosition != position) {
                    getItem(lastClickedItemPosition).isOpen = !getItem(lastClickedItemPosition).isOpen
                    notifyItemChanged(lastClickedItemPosition)
                }
                notifyItemChanged(position)
                lastClickedItemPosition = position

            }

        }

        checkViewVisibility(item, holder, position)


    }


    private fun checkViewVisibility(
        item: VerticalItem,
        holder: StepperViewHolder,
        position: Int
    ) {
        if (item.isOpen) {
            holder.binding.contentFragment.visibility = View.VISIBLE
        } else {
            holder.binding.contentFragment.visibility = View.GONE
        }

        if (item.isOpen) {

            if (position == 0) {
                holder.binding.btnPrev.visibility = View.GONE
            } else {
                holder.binding.btnPrev.visibility = View.VISIBLE
            }
            holder.binding.btnNext.visibility = View.VISIBLE
            holder.binding.contentFragment.visibility = View.VISIBLE

        } else {
            holder.binding.btnNext.visibility = View.GONE
            holder.binding.btnPrev.visibility = View.GONE
            holder.binding.contentFragment.visibility = View.GONE

        }


        if (position == itemCount - 1 || item.isOpen) {
            holder.binding.verticalLine.visibility = View.GONE
        } else {
            holder.binding.verticalLine.visibility = View.VISIBLE
        }


    }

    private fun addFragmentToView(holder: StepperViewHolder, position: Int) {
        removeOldFragment(holder)
        val newContainerId: Int = position + 10000
        holder.binding.contentFragment.id = newContainerId // Set container id

        fragmentManager.beginTransaction().replace(newContainerId, getItem(position).fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun removeOldFragment(holder: StepperViewHolder) {

        val containerId: Int = holder.binding.contentFragment.id // Get container id
        val oldFragment: Fragment? = fragmentManager.findFragmentById(containerId)
        if (oldFragment != null) {
            fragmentManager.beginTransaction().remove(oldFragment).commit()
        }

    }


    class StepperViewHolder(var binding: StepperItemBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.btnNext.text = nextText
            binding.btnPrev.text = prevText
        }
    }


}


class TestDiffCallback : DiffUtil.ItemCallback<VerticalItem>() {
    override fun areItemsTheSame(oldItem: VerticalItem, newItem: VerticalItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VerticalItem, newItem: VerticalItem): Boolean {
        return oldItem == newItem
    }

}