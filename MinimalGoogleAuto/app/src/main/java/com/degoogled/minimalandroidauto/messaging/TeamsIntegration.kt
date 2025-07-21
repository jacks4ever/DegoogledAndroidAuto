package com.degoogled.minimalandroidauto.messaging

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Integration with Microsoft Teams app
 */
class TeamsIntegration(private val context: Context) {

    companion object {
        private const val TAG = "TeamsIntegration"
        private const val TEAMS_PACKAGE = "com.microsoft.teams"
        private const val TEAMS_PLAY_STORE_URL = "https://play.google.com/store/apps/details?id=com.microsoft.teams"
        
        // Deep link schemes
        private const val TEAMS_SCHEME = "msteams:"
    }

    /**
     * Check if Teams is installed
     */
    fun isTeamsInstalled(): Boolean {
        return try {
            context.packageManager.getPackageInfo(TEAMS_PACKAGE, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    /**
     * Get the Play Store intent to install Teams
     */
    fun getTeamsInstallIntent(): Intent {
        return Intent(Intent.ACTION_VIEW, Uri.parse(TEAMS_PLAY_STORE_URL))
    }

    /**
     * Launch Microsoft Teams app
     */
    suspend fun launchTeams() = withContext(Dispatchers.IO) {
        try {
            val intent = context.packageManager.getLaunchIntentForPackage(TEAMS_PACKAGE)
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            } else {
                Log.e(TAG, "Could not create launch intent for Teams")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error launching Teams", e)
        }
    }

    /**
     * Open a chat with a specific user
     * @param userId User ID or email address
     */
    suspend fun openChat(userId: String) = withContext(Dispatchers.IO) {
        try {
            // Format: msteams://l/chat/0/0?users=user@example.com
            val uri = "${TEAMS_SCHEME}//l/chat/0/0?users=$userId"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(uri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Error opening Teams chat", e)
            // Fallback to launching the app
            launchTeams()
        }
    }

    /**
     * Join a Teams meeting
     * @param meetingId Meeting ID
     */
    suspend fun joinMeeting(meetingId: String) = withContext(Dispatchers.IO) {
        try {
            // Format: msteams://l/meeting/0/0?url=https://teams.microsoft.com/l/meetup-join/meeting_id
            val uri = "${TEAMS_SCHEME}//l/meeting/0/0?url=https://teams.microsoft.com/l/meetup-join/$meetingId"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(uri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Error joining Teams meeting", e)
            // Fallback to launching the app
            launchTeams()
        }
    }

    /**
     * Open a specific team
     * @param teamId Team ID
     */
    suspend fun openTeam(teamId: String) = withContext(Dispatchers.IO) {
        try {
            // Format: msteams://l/team/0/0?groupId=team_id
            val uri = "${TEAMS_SCHEME}//l/team/0/0?groupId=$teamId"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(uri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Error opening Teams team", e)
            // Fallback to launching the app
            launchTeams()
        }
    }

    /**
     * Open a specific channel in a team
     * @param teamId Team ID
     * @param channelId Channel ID
     */
    suspend fun openChannel(teamId: String, channelId: String) = withContext(Dispatchers.IO) {
        try {
            // Format: msteams://l/channel/0/0?groupId=team_id&channelId=channel_id
            val uri = "${TEAMS_SCHEME}//l/channel/0/0?groupId=$teamId&channelId=$channelId"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(uri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Error opening Teams channel", e)
            // Fallback to opening the team
            openTeam(teamId)
        }
    }
}