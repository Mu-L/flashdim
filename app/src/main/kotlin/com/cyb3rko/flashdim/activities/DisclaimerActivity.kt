/*
 * Copyright (c) 2022-2026 Cyb3rKo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cyb3rko.flashdim.activities

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.cyb3rko.flashdim.R
import com.cyb3rko.flashdim.databinding.ActivityDisclaimerBinding
import com.cyb3rko.flashdim.utils.Safe

// included in buildType "debug", "release"
// excluded in buildType "libre"
internal class DisclaimerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisclaimerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisclaimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)
        binding.disclaimerMessage.text =
            getString(R.string.disclaimer_message, getString(R.string.dialog_accessibility_message))

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // block back navigation until disclaimer is acknowledged
            }
        })

        binding.acknowledgeButton.setOnClickListener {
            Safe.initialize(applicationContext)
            Safe.writeBoolean(Safe.ACCESSIBILITY_DISCLAIMER_ACKNOWLEDGED, true)
            finish()
        }
    }
}
