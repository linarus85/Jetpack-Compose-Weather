package com.example.aniweather.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.aniweather.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume


@ExperimentalCoroutinesApi
class DefaultLocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
) : LocationTracker {

    @RequiresApi(Build.VERSION_CODES.S)
    override suspend fun getCurrentLocation(): Location? {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission || !isGpsEnabled) {
            return null
        }

        return suspendCancellableCoroutine { cont ->
            locationClient.getCurrentLocation(LocationRequest.QUALITY_HIGH_ACCURACY, null)
                .addOnCompleteListener {
                    cont.resume(it.result)
                }
            //            .apply {
//                if(isComplete) {
//                    if(isSuccessful) {
//                        cont.resume(result)
//                    } else {
//                        cont.resume(null)
//                    }
//                    return@suspendCancellableCoroutine
//                }
//                addOnSuccessListener {
//                    cont.resume(it)
//                }
//                addOnFailureListener {
//                    cont.resume(null)
//                }
//                addOnCanceledListener {
//                    cont.cancel()
//                }
//            }
        }
    }
}