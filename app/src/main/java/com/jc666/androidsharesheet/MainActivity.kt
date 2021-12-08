package com.jc666.androidsharesheet

import android.content.ClipData
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.print.PrintAttributes
import android.widget.Button
import androidx.core.content.FileProvider.getUriForFile
import java.io.File
import java.security.AccessController.getContext
import android.content.pm.ResolveInfo
import android.util.Log


class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    private var btn_share_xml: Button? = null

    private var btn_share_pdf: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_share_xml = findViewById(R.id.btn_share_xml)
        btn_share_pdf = findViewById(R.id.btn_share_pdf)

        //搬移assets底下的檔案到data/data底下
        copyFile("Coroutine.pdf")
        copyFile("Text.xml")

        btn_share_xml!!.setOnClickListener {
            shareXML()
        }

        btn_share_pdf!!.setOnClickListener {
            sharePDF()
        }

    }

    fun Context.copyFile(filename: String) {
        this.assets.open(filename).use { stream ->
            File("${this.filesDir}/$filename").outputStream().use {
                stream.copyTo(it)
            }
        }
    }

    fun shareXML() {
//        val filePath =  getFileFromAssets(this@MainActivity, "Text.xml").absolutePath
//        val uri = Uri.fromFile(File(filePath))

        var internalFilePath = applicationContext.getFilesDir().getAbsolutePath() + File.separator + "Text.xml"

        val contentUri: Uri = getUriForFile(
            this@MainActivity,
            "$packageName.fileprovider", File(internalFilePath)
        )
        onShareXML(contentUri)
    }

    fun sharePDF() {
//        val filePath =  getFileFromAssets(this@MainActivity, "Coroutine.pdf").absolutePath
//        val uri = Uri.fromFile(File(filePath))

        var internalFilePath = applicationContext.getFilesDir().getAbsolutePath() + File.separator + "Coroutine.pdf"

        val contentUri: Uri = getUriForFile(
            this@MainActivity,
            "$packageName.fileprovider", File(internalFilePath)
        )

//        onSharePDF(contentUri)
        openShareChooser(this@MainActivity, "JC", "666", "application/pdf", contentUri)
    }

    fun getFileFromAssets(context: Context, fileName: String): File = File(context.cacheDir, fileName)
        .also {
            if (!it.exists()) {
                it.outputStream().use { cache ->
                    context.assets.open(fileName).use { inputStream ->
                        inputStream.copyTo(cache)
                    }
                }
            }
        }

    fun onSharePDF(pdfUri: Uri) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "application/pdf"

            // (Optional) Here we're setting the title of the content
//            putExtra(Intent.EXTRA_TITLE, "Share PDF file")
//            setClipData(ClipData.newRawUri("", pdfUri))

            putExtra(Intent.EXTRA_STREAM, pdfUri)
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share PDF")
        startActivity(shareIntent)
    }

    fun openShareChooser(context: Context, title: String?, content: String?, type: String, typeUri: Uri) {
        val template = Intent(Intent.ACTION_SEND)
        template.type = type
        template.putExtra(Intent.EXTRA_STREAM, typeUri)
//        template.putExtra(Intent.EXTRA_SUBJECT, title)
//        template.putExtra(Intent.EXTRA_TEXT, content)
        context.startActivity(
            getShareChooserWithoutFacebook(context, template)
        )
    }

    private fun getShareChooserWithoutFacebook(context: Context, template: Intent): Intent {

        // get available share intents
        val targets: MutableList<Intent> = mutableListOf()
        val candidates: List<ResolveInfo> =
            context.packageManager.queryIntentActivities(template, 0)
        val excludedComponents = ArrayList<ComponentName>()

        // remove facebook which has a broken share intent
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.R) {
            for (candidate in candidates) {
                val packageName = candidate.activityInfo.packageName
                //Log.d(TAG, "packageName: " + packageName)
//                if (!packageName.equals("com.facebook.katana")) {
                if (!(packageName.equals("com.android.bluetooth")) ||
                    !(packageName.equals("com.facebook.katana")) ||
                    !(packageName.equals("com.microsoft.office.outlook")) ||
                    !(packageName.equals("com.microsoft.office.word")) ||
                    !(packageName.equals("com.microsoft.office.powerpoint")) ||
                    !(packageName.equals("com.microsoft.office.excel")) ||
                    !(packageName.equals("com.microsoft.office.officelens")) ||
                    !(packageName.equals("com.microsoft.office.onenote")) ||
                    !(packageName.equals("com.microsoft.skydrive")) ||
                    !(packageName.equals("com.microsoft.office.officehubrow")) ||
                    !(packageName.equals("com.samsung.android.app.sharelive")) ||
                    !(packageName.equals("com.google.android.apps.messaging")) ||
                    !(packageName.equals("com.google.android.apps.docs.editors.docs")) ||
                    !(packageName.equals("com.google.android.apps.docs.editors.slides")) ||
                    !(packageName.equals("com.google.android.apps.docs.editors.sheets")) ||
                    !(packageName.equals("com.google.android.apps.docs")) ||
                    !(packageName.equals("com.google.android.documentsui")) ||
                    !(packageName.equals("com.adobe.reader")) ||
                    !(packageName.equals("jp.naver.line.android"))) {
                    val target = Intent(Intent.ACTION_SEND)
                    target.type = "application/pdf"
//                    target.putExtra(
//                        Intent.EXTRA_SUBJECT,
//                        template.getStringExtra(Intent.EXTRA_SUBJECT)
//                    )
//                    target.putExtra(
//                        Intent.EXTRA_TEXT,
//                        template.getStringExtra(Intent.EXTRA_TEXT)
//                    )
                    target.setPackage(packageName)
                    targets.add(target)
                }
            }
        } else {
            for (candidate in candidates) {
                val packageName = candidate.activityInfo.packageName
                //Log.d(TAG, "packageName: " + packageName)
                val name = candidate.activityInfo.name
                if ((packageName.equals("com.android.bluetooth")) ||
                    (packageName.equals("com.facebook.katana")) ||
                    (packageName.equals("com.microsoft.office.outlook")) ||
                    (packageName.equals("com.microsoft.office.word")) ||
                    (packageName.equals("com.microsoft.office.powerpoint")) ||
                    (packageName.equals("com.microsoft.office.excel")) ||
                    (packageName.equals("com.microsoft.office.officelens")) ||
                    (packageName.equals("com.microsoft.office.onenote")) ||
                    (packageName.equals("com.microsoft.skydrive")) ||
                    (packageName.equals("com.microsoft.office.officehubrow")) ||
                    (packageName.equals("com.samsung.android.app.sharelive")) ||
                    (packageName.equals("com.google.android.apps.messaging")) ||
                    (packageName.equals("com.google.android.apps.docs.editors.docs")) ||
                     (packageName.equals("com.google.android.apps.docs.editors.slides")) ||
                    (packageName.equals("com.google.android.apps.docs.editors.sheets")) ||
                    (packageName.equals("com.google.android.apps.docs")) ||
                    (packageName.equals("com.google.android.documentsui")) ||
                    (packageName.equals("com.adobe.reader")) ||
                    (packageName.equals("jp.naver.line.android")) ) {
                    excludedComponents.add(ComponentName(packageName, name))
                }
            }
        }

        var chooserIntent: Intent? = null

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.R) {
            Log.d(TAG, "android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.R")
            chooserIntent =
                Intent.createChooser(targets.removeAt(0), null)
            chooserIntent.putExtra(
                Intent.EXTRA_INITIAL_INTENTS,
                targets.toTypedArray()
            )
        } else {
            chooserIntent = Intent.createChooser(template, null)
            chooserIntent
                .putExtra(Intent.EXTRA_EXCLUDE_COMPONENTS, excludedComponents.toTypedArray())
        }

        return chooserIntent
    }

    //Launch ShareSheet to Share Fact
    fun onShareXML(xmlUri: Uri) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/*"
//            type = "*/*"
            // (Optional) Here we're setting the title of the content
//            putExtra(Intent.EXTRA_TITLE, "Share XML file")
//            setClipData(ClipData.newRawUri("", xmlUri))

            putExtra(Intent.EXTRA_STREAM, xmlUri)
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share XML")
        startActivity(shareIntent)
    }



}