package com.bilingoal.sharedviewmodel;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import com.bilingoal.sharedviewmodel.databinding.FragmentTaskListBinding;
import com.bilingoal.sharedviewmodel.databinding.TaskListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class TaskListFragment extends Fragment {
    private FragmentTaskListBinding binding;
    private DetailsFragment detailsFragment = new DetailsFragment();

    public TaskListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        List<Item> list = new ArrayList<>();
        list.add(new Item("Senin", "Data Warehouse", "Data warehouse adalah mapel semester 5"));
        list.add(new Item("Rabu", "PPL", "PPL adalah mapel semester 5"));
        list.add(new Item("Jumat", "Proyek 2", "Proyek 2 adalah mapel semester 5"));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list);
        binding.recyclerView.setAdapter(adapter);
        adapter.setListener((v, position) -> {
            viewModel.setSelected(adapter.getItemAt(position));
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container, detailsFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }
}
