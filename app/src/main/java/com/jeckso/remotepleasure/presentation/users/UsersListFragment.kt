package com.jeckso.remotepleasure.presentation.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jeckso.remotepleasure.android.adapter.OnItemClickListener
import com.jeckso.remotepleasure.databinding.FragmentUserListBinding
import com.jeckso.remotepleasure.presentation.base.fragment.BaseFragment
import com.jeckso.remotepleasure.presentation.users.utils.UserVM
import com.jeckso.remotepleasure.presentation.users.utils.UsersListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class UsersListFragment private constructor(): BaseFragment<UserListViewModel, FragmentUserListBinding>(),
    OnItemClickListener<UserVM>,
    SwipeRefreshLayout.OnRefreshListener {

    companion object {

        const val TAG = "UsersListFragment"

        fun arguments() = Bundle()

        fun newInstance(arguments: Bundle = Bundle()) : UsersListFragment {
            val fragment = UsersListFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    override val viewModel: UserListViewModel by viewModels()

    private val usersAdapter = UsersListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.users.collect(::onUsersReady)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.usersListRecyclerView.adapter = usersAdapter
        viewBinding.swipeRefreshLayout.setOnRefreshListener(this)
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentUserListBinding {
        return FragmentUserListBinding.inflate(inflater, container, false)
    }

    override fun onItemClick(item: UserVM, position: Int) {
        viewModel.connect(item)
    }

    private fun onUsersReady(users: List<UserVM>) {
        usersAdapter.items = users
    }

    override fun onRefresh() {
        viewModel.refresh()
    }

    override fun shouldShowProgress(isVisible: Boolean) {
        viewBinding.swipeRefreshLayout.isRefreshing = isVisible
    }
}