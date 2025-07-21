package com.degoogled.minimalandroidauto.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.degoogled.minimalandroidauto.R
import com.degoogled.minimalandroidauto.utils.DebugLogger
import com.degoogled.minimalandroidauto.utils.logInfo
import java.io.File

/**
 * Debug activity for viewing logs and diagnostic information
 */
class DebugActivity : AppCompatActivity() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var logAdapter: LogAdapter
    private lateinit var filterSpinner: Spinner
    private lateinit var clearButton: Button
    private lateinit var exportButton: Button
    private lateinit var systemInfoText: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug)
        
        // Set up action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Debug Console"
        
        // Initialize views
        recyclerView = findViewById(R.id.log_recycler_view)
        filterSpinner = findViewById(R.id.log_filter_spinner)
        clearButton = findViewById(R.id.clear_logs_button)
        exportButton = findViewById(R.id.export_logs_button)
        systemInfoText = findViewById(R.id.system_info_text)
        
        // Set up recycler view
        recyclerView.layoutManager = LinearLayoutManager(this)
        logAdapter = LogAdapter(DebugLogger.getInstance().getLogEntries())
        recyclerView.adapter = logAdapter
        
        // Set up filter spinner
        val filterOptions = arrayOf("All Logs", "Debug", "Info", "Warning", "Error", "Critical")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, filterOptions)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        filterSpinner.adapter = spinnerAdapter
        
        // Set up filter listener
        filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filterLogs(position)
            }
            
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
        
        // Set up clear button
        clearButton.setOnClickListener {
            showClearLogsConfirmation()
        }
        
        // Set up export button
        exportButton.setOnClickListener {
            exportLogs()
        }
        
        // Display system info
        displaySystemInfo()
        
        // Log activity creation
        logInfo("Debug activity created")
    }
    
    override fun onResume() {
        super.onResume()
        refreshLogs()
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.debug_menu, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_refresh -> {
                refreshLogs()
                true
            }
            R.id.action_test_connection -> {
                testConnection()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    /**
     * Refresh the logs display
     */
    private fun refreshLogs() {
        logAdapter.updateLogs(DebugLogger.getInstance().getLogEntries())
        recyclerView.scrollToPosition(logAdapter.itemCount - 1)
    }
    
    /**
     * Filter logs by level
     */
    private fun filterLogs(filterPosition: Int) {
        val allLogs = DebugLogger.getInstance().getLogEntries()
        val filteredLogs = when (filterPosition) {
            0 -> allLogs // All logs
            1 -> allLogs.filter { it.level == DebugLogger.Companion.Level.DEBUG }
            2 -> allLogs.filter { it.level == DebugLogger.Companion.Level.INFO }
            3 -> allLogs.filter { it.level == DebugLogger.Companion.Level.WARNING }
            4 -> allLogs.filter { it.level == DebugLogger.Companion.Level.ERROR }
            5 -> allLogs.filter { it.level == DebugLogger.Companion.Level.CRITICAL }
            else -> allLogs
        }
        
        logAdapter.updateLogs(filteredLogs)
        recyclerView.scrollToPosition(logAdapter.itemCount - 1)
    }
    
    /**
     * Show confirmation dialog before clearing logs
     */
    private fun showClearLogsConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Clear Logs")
            .setMessage("Are you sure you want to clear all logs?")
            .setPositiveButton("Clear") { _, _ ->
                DebugLogger.getInstance().clearLogs()
                refreshLogs()
                Toast.makeText(this, "Logs cleared", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    /**
     * Export logs to a file and share
     */
    private fun exportLogs() {
        val exportFile = DebugLogger.getInstance().exportLogs(this)
        
        if (exportFile != null) {
            // Share the exported file
            val fileUri = FileProvider.getUriForFile(
                this,
                "${packageName}.fileprovider",
                exportFile
            )
            
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri)
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            
            startActivity(Intent.createChooser(shareIntent, "Share Logs"))
        } else {
            Toast.makeText(this, "Failed to export logs", Toast.LENGTH_SHORT).show()
        }
    }
    
    /**
     * Display system information
     */
    private fun displaySystemInfo() {
        val runtime = Runtime.getRuntime()
        val maxMemory = runtime.maxMemory() / (1024 * 1024)
        val totalMemory = runtime.totalMemory() / (1024 * 1024)
        val freeMemory = runtime.freeMemory() / (1024 * 1024)
        
        val info = StringBuilder()
        info.append("Device: ${android.os.Build.MANUFACTURER} ${android.os.Build.MODEL}\n")
        info.append("Android: ${android.os.Build.VERSION.RELEASE} (API ${android.os.Build.VERSION.SDK_INT})\n")
        info.append("Memory: Max ${maxMemory}MB, Used ${totalMemory - freeMemory}MB, Free ${freeMemory}MB\n")
        
        try {
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            info.append("App Version: ${packageInfo.versionName} (${packageInfo.versionCode})\n")
        } catch (e: Exception) {
            info.append("App Version: Unknown\n")
        }
        
        systemInfoText.text = info.toString()
    }
    
    /**
     * Test connection to car
     */
    private fun testConnection() {
        logInfo("Manual connection test initiated")
        
        // Start the service to test connection
        val intent = Intent("com.degoogled.minimalandroidauto.action.TEST_CONNECTION")
        intent.setPackage(packageName)
        startService(intent)
        
        Toast.makeText(this, "Connection test started", Toast.LENGTH_SHORT).show()
        
        // Refresh logs after a delay
        recyclerView.postDelayed({
            refreshLogs()
        }, 3000)
    }
    
    /**
     * Adapter for displaying logs in a RecyclerView
     */
    private inner class LogAdapter(private var logs: List<DebugLogger.LogEntry>) : 
            RecyclerView.Adapter<LogAdapter.LogViewHolder>() {
        
        override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): LogViewHolder {
            val view = layoutInflater.inflate(R.layout.item_log, parent, false)
            return LogViewHolder(view)
        }
        
        override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
            val logEntry = logs[position]
            holder.bind(logEntry)
        }
        
        override fun getItemCount(): Int = logs.size
        
        fun updateLogs(newLogs: List<DebugLogger.LogEntry>) {
            logs = newLogs
            notifyDataSetChanged()
        }
        
        inner class LogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val timestampText: TextView = itemView.findViewById(R.id.log_timestamp)
            private val levelText: TextView = itemView.findViewById(R.id.log_level)
            private val tagText: TextView = itemView.findViewById(R.id.log_tag)
            private val messageText: TextView = itemView.findViewById(R.id.log_message)
            
            fun bind(logEntry: DebugLogger.LogEntry) {
                timestampText.text = logEntry.timestamp
                levelText.text = logEntry.level.toString()
                tagText.text = logEntry.tag
                messageText.text = logEntry.message
                
                // Set color based on log level
                val color = when (logEntry.level) {
                    DebugLogger.Companion.Level.DEBUG -> R.color.log_debug
                    DebugLogger.Companion.Level.INFO -> R.color.log_info
                    DebugLogger.Companion.Level.WARNING -> R.color.log_warning
                    DebugLogger.Companion.Level.ERROR -> R.color.log_error
                    DebugLogger.Companion.Level.CRITICAL -> R.color.log_critical
                    else -> R.color.log_debug
                }
                levelText.setTextColor(resources.getColor(color, theme))
                
                // Show exception details in a dialog when clicked
                if (logEntry.throwable != null) {
                    itemView.setOnClickListener {
                        showExceptionDialog(logEntry)
                    }
                    itemView.isClickable = true
                    messageText.setTextColor(resources.getColor(R.color.log_clickable, theme))
                } else {
                    itemView.isClickable = false
                    messageText.setTextColor(resources.getColor(R.color.log_message, theme))
                }
            }
            
            private fun showExceptionDialog(logEntry: DebugLogger.LogEntry) {
                if (logEntry.throwable != null) {
                    val stackTrace = logEntry.throwable.stackTraceToString()
                    
                    AlertDialog.Builder(this@DebugActivity)
                        .setTitle("Exception Details")
                        .setMessage(stackTrace)
                        .setPositiveButton("Copy") { dialog, which ->
                            val clipboard = getSystemService(CLIPBOARD_SERVICE) as android.content.ClipboardManager
                            val clip = android.content.ClipData.newPlainText("Exception Details", stackTrace)
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(this@DebugActivity, "Copied to clipboard", Toast.LENGTH_SHORT).show()
                        }
                        .setNegativeButton("Close", null)
                        .show()
                }
            }
        }
    }
}