package com.example.mygithubuser.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuser.koneksi.KoneksiConfig
import com.example.mygithubuser.databinding.FragmentFollowingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Fragment_Following : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val kunci = requireActivity().intent.getStringExtra(DetailUserActivity.UsernameUser).toString()
        Toast.makeText(activity, kunci, Toast.LENGTH_SHORT).show()
        tampildata(kunci)
    }

    private fun tampildata(kunci: String) {
        val apiservice = KoneksiConfig.getApiService().getFollowing(kunci)
        apiservice.enqueue(object :Callback<List<ResponseFollowingItem>>{
            override fun onResponse(
                call: Call<List<ResponseFollowingItem>>,
                response: Response<List<ResponseFollowingItem>>
            ) {
                val data = response.body()

                if (data != null) {
                    if (response.isSuccessful) {
                        showLoading(false)
                        Toast.makeText(
                            activity,
                            "sukses koneksi fragment following",
                            Toast.LENGTH_SHORT
                        ).show()
                        setdataa(data)
                    } else {
                        Toast.makeText(activity, data.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onFailure(call: Call<List<ResponseFollowingItem>>, t: Throwable) {
                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

    private fun setdataa(items: List<ResponseFollowingItem>){
        binding.listfollowing.setHasFixedSize(true)
        Log.i("TAG", "setFollowerList: ${items?.size}")
        val layoutManager = LinearLayoutManager(context)
        binding.listfollowing.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        binding.listfollowing.addItemDecoration(itemDecoration)
        val adapter = FollowingAdapter(items)
        binding.listfollowing.adapter = adapter
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }

}