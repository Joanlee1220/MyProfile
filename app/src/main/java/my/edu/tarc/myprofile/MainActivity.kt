package my.edu.tarc.myprofile

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import my.edu.tarc.myprofile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //private var myProfile = Profile()
    val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var myPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("Main", "OnCreate")

        binding.buttonSave.setOnClickListener {
            with(profileViewModel.profile){
                name = binding.editTextName.text.toString()
                email = binding.editTextEmail.text.toString()
                phone = binding.editTextPhone.text.toString()
            }
            binding.textViewName.text = profileViewModel.profile.name
            binding.textViewEmail.text = profileViewModel.profile.email
            binding.textViewPhone.text = profileViewModel.profile.phone

            with(myPreference.edit()){
                putString(getString(R.string.name), binding.textViewName.text.toString())
                putString(getString(R.string.email), binding.textViewEmail.text.toString())
                putString(getString(R.string.phone), binding.textViewPhone.text.toString())
                apply()
            }
        }
    }
    override  fun onResume() {
        super.onResume()
        //If you have more than one Activity
        //myPreference = this.getSharedPreferences("myRefFile", Context.MODE_PRIVATE)
        //If you have one Activity
        myPreference = this.getPreferences(Context.MODE_PRIVATE)
        //Read the shared preference values
        binding.textViewName.text = myPreference.getString(getString(R.string.name),"")
        binding.textViewEmail.text = myPreference.getString(getString(R.string.email),"")
        binding.textViewPhone.text = myPreference.getString(getString(R.string.phone),"")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d("Main", "onDestroy")
    }
}