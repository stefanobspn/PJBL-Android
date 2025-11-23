package dev.stefano.asesmengasal

import android.os.Bundle

class ProfileActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setChildContent(R.layout.activity_profile)

        currentNavId = R.id.profile_btn
        setToolbarTitle("Profile")

    }
}