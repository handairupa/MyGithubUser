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
import com.example.mygithubuser.databinding.FragmentFollowerBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Fragment_Follower : Fragment() {

    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val kunci = requireActivity().intent.getStringExtra(DetailUserActivity.UsernameUser).toString()
        Toast.makeText(activity, kunci, Toast.LENGTH_SHORT).show()
        tampildata(kunci)
    }

    private fun tampildata(kunci: String) {
        val apiservice = KoneksiConfig.getApiService().getFollower(kunci)
        apiservice.enqueue(object :Callback<List<ResponseFollowersItem>>{
            override fun onResponse(
                call: Call<List<ResponseFollowersItem>>,
                response: Response<List<ResponseFollowersItem>>
            ) {
                val data = response.body()

                if (data != null) {
                    if (response.isSuccessful) {
                        showLoading(false)
                        Toast.makeText(
                            activity,
                            "sukses koneksi fragment follower",
                            Toast.LENGTH_SHORT
                        ).show()
                        setdataa(data)
                    } else {
                        Toast.makeText(activity, data.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<List<ResponseFollowersItem>>, t: Throwable) {
                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    private fun setdataa(items: List<ResponseFollowersItem>){
        binding.listfollower.setHasFixedSize(true)
        Log.i("TAG", "setFollowerList: ${items?.size}")
        val layoutManager = LinearLayoutManager(context)
        binding.listfollower.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        binding.listfollower.addItemDecoration(itemDecoration)
        val adapter = FollowersAdapter(items)
        binding.listfollower.adapter = adapter
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