package com.example.projecttraining2.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.projecttraining2.R
import com.example.projecttraining2.UserPreference
import com.example.projecttraining2.databinding.FragmentProfileBinding
import com.example.projecttraining2.ui.wishlist.DashboardViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private lateinit var usernameSession : TextView
    private lateinit var userPref : UserPreference

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        _binding = FragmentProfileBinding.inflate(layoutInflater)

        usernameSession = root.findViewById(R.id.usernameSession)
        userPref = UserPreference(requireContext())

//        usernameSession.text = userPref.getLogin().usernameLogin.toString()

//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usernameSession.text = "Welcome back, ${userPref.getLogin().usernameLogin.toString()}"
//        if(userPref.getLogin().isLogin){
            Log.d("test", "welcome back, ${userPref.getLogin().usernameLogin}")
//            usernameSession.text = userPref.getLogin().usernameLogin.toString()
//        }
//        else {
//            usernameSession.text = "nothing"
//        }
//        if(userPref.getLogin().isLogin){
//            usernameSession.text = "Welcome back, ${userPref.getLogin().usernameLogin}"
//        } else {
//            usernameSession.text = "nothing"
//        }

    }

//    fun logout(view: View){
//        Log.d("Logout", "onclick")
//        val userPreference = UserPreference(view.context)
//        val getlogin = userPreference.getLogin()
//        val setlogin = IsLogin()
//        setlogin.isLogin = false
//        if (getlogin.isLogin) {
//            userPreference.setLogin(setlogin)
//            startActivity(Intent(view.context, LoginActivity::class.java))
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}