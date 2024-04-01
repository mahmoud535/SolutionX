package com.example.solutionx.presentation.ui.activity.main

import com.example.solutionx.android.helpers.logging.getClassLogger
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.solutionx.BuildConfig
import com.example.solutionx.R
import com.example.solutionx.features.services.presentation.productflavors.LoggerProvider
import dagger.hilt.android.AndroidEntryPoint

//import dagger.hilt.android.AndroidEntryPoint

//import com.example.solutionx.presentation.activity.CustomLogger

@AndroidEntryPoint
 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        logger.debug("This is a debug log....")
        logger.info("This is an info log....")
        logger.warning("This is a warning log....")
        logger.error("This is an error log....")
        logger.error("This is an error log with throwable....", Throwable("CustomException....."))

        val logger = LoggerProvider.getLogger()
        logger.log("Hello from ${BuildConfig.FLAVOR} flavor!")


    }

    companion object {
        private val logger = getClassLogger()
    }



/**   Feature [data, domain , presentation]
 *   Data, Domain [mapper , models , repository "local, remote", interactor]

 * *login* user[gender = 0,1,2]

Data:
- mappers ->
- repository -->
 *  *    - localDs  -> entity UserEntity
 *  *  - remoteDs  ->  retrofit
 *  - models--> [dto, entity]

Domain :
- interactor --> usecases [ emaillogin, phonelogin , socialLogin]
      - repository -->
      -localDs -->
      remoteDs -->
      models     -->  [domain]

User[dender = enumGender()]

    UserFeature :
         - User Dto (Data Transfer Object) --> DTO
         - User Entity --> Entity
         - User --> Domain

**/
}