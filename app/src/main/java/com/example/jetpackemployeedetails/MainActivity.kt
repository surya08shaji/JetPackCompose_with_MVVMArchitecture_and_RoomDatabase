package com.example.jetpackemployeedetails

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.jetpackemployeedetails.model.DataModel
import com.example.jetpackemployeedetails.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val employeeList = mutableStateListOf<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintLayout(list = employeeList)
        }
        observeUserDetails()
        observeCount()
        observeLocalUserDetails()
        viewModel.getAllDataCount()
    }

    private fun observeLocalUserDetails() {
        viewModel.userDetailsLocalLiveData.observe(this) {
            it?.let { it1 ->
                employeeList.addAll(it1)
            }
        }
    }

    private fun observeCount() {
        viewModel.userDetailsLiveDataCount.observe(this) {
            if ((it ?: 0) > 0) {
                viewModel.getAllData()
            } else {
                viewModel.fetchUserDetails()
            }
        }
    }


    private fun observeUserDetails() {
        viewModel.userDetailsLiveData.observe(this) {
            if (it != null) {
                Log.e("userDetails", "${it.size}")
                viewModel.insertDetails(it)
            }
        }
    }
}

@Composable
fun ConstraintLayout(list: SnapshotStateList<DataModel>) {

    androidx.constraintlayout.compose.ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (topBar, lazyColumn) = createRefs()

        TopAppBar(
            title = { Text(text = "UserList") },
            navigationIcon = null,
            backgroundColor = Color.LightGray,
            contentColor = Color.Black,
            modifier = Modifier
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        LazyColumn(modifier = Modifier
            .constrainAs(lazyColumn) {
                top.linkTo(topBar.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            items(list) { item ->

                UserItem(item = item)
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun UserItem(item: DataModel) {
    val context = LocalContext.current

    androidx.constraintlayout.compose.ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable(onClick = {

                val intent = Intent(context, ProfileDetail::class.java)

                intent.putExtra("image", item.profileImage)
                intent.putExtra("name", item.name)
                intent.putExtra("username", item.username)
                intent.putExtra("email", item.email)
                intent.putExtra("street", item.address?.street)
                intent.putExtra("suite", item.address?.suite)
                intent.putExtra("city", item.address?.city)
                intent.putExtra("zipcode", item.address?.zipcode)
                intent.putExtra("phone", item.phone)
                intent.putExtra("website", item.website)
                intent.putExtra("companyname", item.company?.name)
                intent.putExtra("catchPhrase", item.company?.catchPhrase)
                intent.putExtra("bs", item.company?.bs)

                context.startActivity(intent)
            }),
            elevation = 4.dp) {
            Row(modifier = Modifier.padding(4.dp)) {

                androidx.constraintlayout.compose.ConstraintLayout(
                    modifier = Modifier.fillMaxSize() ) {

                    val(profile) = createRefs()
                    val image = rememberImagePainter(item.profileImage)

                    CircleCropTransformation()

                    Image(painter = image ,
                        contentDescription ="Image Url",
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .constrainAs(profile) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
//                                end.linkTo(parent.end)
                            }
//                    contentScale = ContentScale.Crop
                    )

                    val (name) = createRefs()
                    Text(text = item.name ?: "", fontWeight = FontWeight.Bold, modifier = Modifier
                        .constrainAs(name) {
                            top.linkTo(parent.top)
                            start.linkTo(profile.start)
                            end.linkTo(parent.end)
//                            width = Dimension.fillToConstraints
                        }
                        .padding(8.dp))

                    val (companyname) = createRefs()
                    Text(text = item.company?.name ?: " ", fontWeight = FontWeight.SemiBold, modifier = Modifier
                        .constrainAs(companyname) {
                            top.linkTo(name.bottom)
                            start.linkTo(profile.start)
                            end.linkTo(parent.end)
//                            width = Dimension.fillToConstraints
                        }
                        .padding(8.dp))

                }

            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}