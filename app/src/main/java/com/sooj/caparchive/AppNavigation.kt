package com.sooj.caparchive

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation(
    navController : NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.FolderList.route
    ) {
        // 메일 폴더 리스트
        composable(Routes.FolderList.route) {
            FolderListScreen(navController = navController)
        }

        // 개별 폴더의 이미지 리스트 화면
        composable(
            route = Routes.ImageList.route,
            arguments = listOf(
                navArgument("folderName") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val folderName = backStackEntry.arguments?.getString("folderName") ?: ""
            ImageListScreen(
                navController = navController,
                folderName = folderName,
            )
        }

        // 이미지 상세
        composable(
            route = Routes.ImageDetail.route,
            arguments = listOf(
                navArgument("imageId") {
                    type = NavType.StringType
                    nullable = false
                }
            )
            // 전달된 데이터 읽어오는 객체
        ) { backStackEntry ->
            val folderName = backStackEntry.arguments?.getString("imageId") ?: ""
            ImageListScreen(
                navController = navController,
                folderName = folderName,
            )
        }

        // 이미지 상세 화면 (OCR 텍스트 포함)
        composable(
            route = Routes.ImageDetail.route,
            arguments = listOf(
                navArgument("imageId") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val imageId = backStackEntry.arguments?.getString("imageId") ?: ""
            ImageDetailScreen(
                navController = navController,
                imageId = imageId
            )
        }
    }
}

// navigation routes
sealed class Routes(val route : String) {
    object FolderList : Routes("folder_list")
    object ImageList : Routes("image_list/{folderName}") {
        fun createRoute(folderName: String) = "image_list/$folderName"
    }
    object ImageDetail : Routes("image_detail/{imageId}") {
        fun createRoute(imageId: String) = "image_detail/$imageId"
    }
}