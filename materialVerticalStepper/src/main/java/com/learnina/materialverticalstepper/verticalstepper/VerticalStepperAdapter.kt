package com.learnina.materialverticalstepper.verticalstepper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.learnina.materialverticalstepper.databinding.StepperItemBinding

class VerticalStepperAdapter(
    private val fragmentManager: FragmentManager
) : ListAdapter<VerticalItem, VerticalStepperAdapter.TestViewHolder>(
    TestDiffCallback()
) {


    private var lastClickedItemPostion = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: StepperItemBinding = StepperItemBinding.inflate(inflater, parent, false)
        return TestViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.item = item
//        removeOldFragment(holder, position)

        if (item.isOpen) {
            addFragmentToView(holder, position)
            addFragmentToView(holder, position)
        }

        holder.binding.btnNext.setOnClickListener {
            if (position < itemCount - 1) {
                val nextItem = getItem(position + 1)
                nextItem.isOpen = true
                item.isOpen = false
                notifyItemChanged(position)
                notifyItemChanged(position + 1)
                lastClickedItemPostion = position + 1

            }

        }
        holder.binding.btnPrev.setOnClickListener {
            if (position > 0) {

                val prevItem = getItem(position - 1)
                prevItem.isOpen = true
                item.isOpen = false
                notifyItemChanged(position)
                notifyItemChanged(position - 1)
                lastClickedItemPostion = position - 1

            }
        }

        holder.binding.circleImage.setOnClickListener {
            item.isOpen = !item.isOpen

            if (lastClickedItemPostion != -1 && getItem(lastClickedItemPostion).isOpen && lastClickedItemPostion != position) {
                getItem(lastClickedItemPostion).isOpen = !getItem(lastClickedItemPostion).isOpen
                notifyItemChanged(lastClickedItemPostion)
            }
            notifyItemChanged(position)
            lastClickedItemPostion = position

        }

        checkViewVisibility(item, holder, position)


    }

    private fun checkViewVisibility(
        item: VerticalItem,
        holder: TestViewHolder,
        position: Int
    ) {
        if (item.isOpen) {
            holder.binding.contentFragment.visibility = View.VISIBLE
        } else {
            holder.binding.contentFragment.visibility = View.GONE
        }

        if (item.isOpen) {

            if (position >= (itemCount - 1)) {
                holder.binding.btnNext.visibility = View.GONE
            } else {
                holder.binding.btnNext.visibility = View.VISIBLE
            }

            if (position <= 0) {
                holder.binding.btnPrev.visibility = View.GONE
            } else {
                holder.binding.btnPrev.visibility = View.VISIBLE
            }

        } else {
            holder.binding.btnNext.visibility = View.GONE
            holder.binding.btnPrev.visibility = View.GONE
        }


        if (position == itemCount-1 || item.isOpen){
            holder.binding.verticalLine.visibility = View.GONE
        }else{
            holder.binding.verticalLine.visibility = View.VISIBLE
        }
    }

    private fun addFragmentToView(holder: TestViewHolder, position: Int) {
        removeOldFragment(holder)
        val newContainerId: Int = position + 10000
        holder.binding.contentFragment.id = newContainerId // Set container id

        fragmentManager.beginTransaction().replace(newContainerId, getItem(position).fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun removeOldFragment(holder: TestViewHolder) {

        val containerId: Int = holder.binding.contentFragment.id // Get container id
        val oldFragment: Fragment? = fragmentManager.findFragmentById(containerId)
        if (oldFragment != null) {
            fragmentManager.beginTransaction().remove(oldFragment).commit()
        }

    }


    class TestViewHolder(var binding: StepperItemBinding) : RecyclerView.ViewHolder(binding.root)


}


class TestDiffCallback : DiffUtil.ItemCallback<VerticalItem>() {
    override fun areItemsTheSame(oldItem: VerticalItem, newItem: VerticalItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VerticalItem, newItem: VerticalItem): Boolean {
        return oldItem == newItem
    }

}