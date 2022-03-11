package com.jc666.androidsharesheet

import android.media.MediaScannerConnection
import android.util.Log
import android.util.Xml
import org.xmlpull.v1.XmlSerializer
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception

/**
 *
 * GenerateBriteMedXMLMethod 專門產生xml檔案 code
 *
 * @author JC666
 */

object GenerateBriteMedXMLMethod {
    private val TAG = GenerateBriteMedXMLMethod::class.java.simpleName

    private val enter = System.getProperty("line.separator") //换行

    //JC666 Tag，代表需要額外組合數值出來
    fun generateBriteMEDXMLFile(filePath: String, fileName: String) {
        val file = File(filePath, fileName)
        try {
            val fos = FileOutputStream(file)
            //create XmlSerializer class
            val xs = Xml.newSerializer()
            xs.setOutput(fos, "utf-8")
            //產生xml的標頭
            xs.startDocument("utf-8", true)
            changeLine(xs, enter)

            //開始新增xml節點，AnnotatedECG
            xs.startTag(null, "AnnotatedECG") // <AnnotatedECG xmlns="urn:hl7-org:v3" xmlns:voc="urn:hl7-org:v3/voc" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="urn:hl7-org:v3 ../schema/PORT_MT020001.xsd" type="Observation">
            xs.attribute(null,"xmlns", "urn:hl7-org:v3")
            xs.attribute(null,"xmlns", "urn:hl7-org:v3/voc")
            xs.attribute(null,"xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
            xs.attribute(null,"xsi:schemaLocation", "urn:hl7-org:v3 ../schema/PORT_MT020001.xsd")
            xs.attribute(null,"type", "Observation")
            changeLine(xs, enter)

            xs.startTag(null, "id") // <id root="3ed3ef19-5fe4-4b9b-9504-683ea4a4962e"/>
            xs.attribute(null,"root", "3ed3ef19-5fe4-4b9b-9504-683ea4a4962e")
            xs.endTag(null, "id")
            changeLine(xs, enter)

            xs.startTag(null, "code") // <code code="93000" codeSystem="2.16.840.1.113883.6.12" codeSystemName="CPT-4"/>
            xs.attribute(null,"code", "93000")
            xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.12")
            xs.attribute(null,"codeSystemName", "CPT-4")
            xs.endTag(null, "code")
            changeLine(xs, enter)

            xs.startTag(null, "text") // <text/>
            xs.endTag(null, "text")
            changeLine(xs, enter)

            ///////////////////////////// <effectiveTime> /////////////////////////////////////////
            xs.startTag(null, "effectiveTime") // <effectiveTime>
            changeLine(xs, enter)
            xs.startTag(null, "low") // <low value="20220309154929.000" inclusive="true"/>
            xs.attribute(null,"value", "20220309154929.000") /**JC666 這裡(20220309154929.000)需要換算時間，時間 + ".000"!!!*/
            xs.attribute(null,"inclusive", "true")
            xs.endTag(null, "low")
            changeLine(xs, enter)
            xs.startTag(null, "high") // <high value="20220309154939.999" inclusive="false"/>
            xs.attribute(null,"value", "20220309154939.999") /**JC666 這裡(20220309154939.999)需要換算時間，時間 + ".999"!!!*/
            xs.attribute(null,"inclusive", "false")
            xs.endTag(null, "high")
            changeLine(xs, enter)
            xs.endTag(null, "effectiveTime") // </effectiveTime>
            changeLine(xs, enter)
            ///////////////////////////// </effectiveTime> /////////////////////////////////////////

            xs.startTag(null, "confidentialityCode") // <confidentialityCode/>
            xs.endTag(null, "confidentialityCode")
            changeLine(xs, enter)

            ///////////////////////////// <componentOf> /////////////////////////////////////////
            xs.startTag(null, "componentOf") // <componentOf>
            changeLine(xs, enter)
            xs.startTag(null, "timepointEvent") // <timepointEvent>
            changeLine(xs, enter)
            xs.startTag(null, "componentOf") // <componentOf>
            changeLine(xs, enter)
            xs.startTag(null, "subjectAssignment") // <subjectAssignment>，底下依序有三大類 <subject>, <definition>, <componentOf>
            changeLine(xs, enter)

            ///////////////////////////// <subject> /////////////////////////////////////////
            xs.startTag(null, "subject") // <subject>
            changeLine(xs, enter)
            xs.startTag(null, "trialSubject") // <trialSubject>
            changeLine(xs, enter)
            xs.startTag(null, "id") // <id extension="1234321"/>
            xs.attribute(null,"extension", "1234321") /**JC666 這裡(1234321)需要帶入病患ID，Patient id!!!*/
            xs.endTag(null, "id")
            changeLine(xs, enter)
            xs.startTag(null, "subjectDemographicPerson") // <subjectDemographicPerson>
            changeLine(xs, enter)
            xs.startTag(null, "name") // <name>Yukai Li</name>
            xs.text("Yukai Li") /**JC666 這裡(Yukai Li)需要帶入病患全名，Last name + First name(中文也判斷)!!!*/
            xs.endTag(null, "name")
            changeLine(xs, enter)
            xs.startTag(null, "administrativeGenderCode") // <administrativeGenderCode code="M" codeSystem="2.16.840.1.113883.5.1" codeSystemName="administrativeGender"/>
            xs.attribute(null,"code", "M") /**JC666 這裡(M)需要帶入病患性別，M or F!!!*/
            xs.attribute(null,"codeSystem", "2.16.840.1.113883.5.1")
            xs.attribute(null,"codeSystemName", "administrativeGender")
            xs.endTag(null, "administrativeGenderCode")
            changeLine(xs, enter)
            xs.startTag(null, "birthTime") // <birthTime value="20090503000000"/>
            xs.attribute(null,"value", "20090503000000") /**JC666 這裡(20090503000000)需要帶入病患生日，Patient birthTime!!!*/
            xs.endTag(null, "birthTime")
            changeLine(xs, enter)
            xs.startTag(null, "age") // <age value="12"/>
            xs.attribute(null,"value", "12") /**JC666 這裡(12)需要帶入病患年紀，Patient age!!!*/
            xs.endTag(null, "age")
            changeLine(xs, enter)
            xs.endTag(null, "subjectDemographicPerson") // </subjectDemographicPerson>
            changeLine(xs, enter)
            xs.endTag(null, "trialSubject") // </trialSubject>
            changeLine(xs, enter)
            xs.endTag(null, "subject") // </subject>
            changeLine(xs, enter)
            ///////////////////////////// </subject> /////////////////////////////////////////

            ///////////////////////////// <definition> /////////////////////////////////////////
            xs.startTag(null, "definition") // <definition>
            changeLine(xs, enter)
            xs.startTag(null, "treatmentGroupAssignment") // <treatmentGroupAssignment>
            changeLine(xs, enter)
            xs.startTag(null, "code") // <code>
            xs.endTag(null, "code") // </code>
            changeLine(xs, enter)
            xs.endTag(null, "treatmentGroupAssignment") // </treatmentGroupAssignment>
            changeLine(xs, enter)
            xs.endTag(null, "definition") // </definition>
            changeLine(xs, enter)
            ///////////////////////////// </definition> /////////////////////////////////////////

            ///////////////////////////// <componentOf> /////////////////////////////////////////
            xs.startTag(null, "componentOf") // <componentOf>
            changeLine(xs, enter)
            xs.startTag(null, "clinicalTrial") // <clinicalTrial>
            changeLine(xs, enter)
            xs.startTag(null, "id") // <id/>
            xs.endTag(null, "id") //
            changeLine(xs, enter)
            xs.startTag(null, "location") // <location>
            changeLine(xs, enter)
            xs.startTag(null, "trialSite") // <trialSite>
            changeLine(xs, enter)
            xs.startTag(null, "id") // <id>
            xs.endTag(null, "id") //
            changeLine(xs, enter)
            xs.endTag(null, "trialSite") // </trialSite>
            changeLine(xs, enter)
            xs.endTag(null, "location") // </location>
            changeLine(xs, enter)
            xs.endTag(null, "clinicalTrial") // </clinicalTrial>
            changeLine(xs, enter)
            xs.endTag(null, "componentOf") // </componentOf>
            changeLine(xs, enter)
            ///////////////////////////// </componentOf> /////////////////////////////////////////

            xs.endTag(null, "subjectAssignment") // </subjectAssignment>
            changeLine(xs, enter)
            xs.endTag(null, "componentOf") // </componentOf>
            changeLine(xs, enter)
            xs.endTag(null, "timepointEvent") // </timepointEvent>
            changeLine(xs, enter)
            xs.endTag(null, "componentOf") // </componentOf>
            changeLine(xs, enter)
            ///////////////////////////// </componentOf> /////////////////////////////////////////

            ///////////////////////////// <location> /////////////////////////////////////////
            xs.startTag(null, "location") // <location>
            changeLine(xs, enter)
            xs.startTag(null, "testingSite") // <testingSite>
            changeLine(xs, enter)
            xs.startTag(null, "id") // <id>
            xs.endTag(null, "id") //
            changeLine(xs, enter)
            xs.endTag(null, "testingSite") // </testingSite>
            changeLine(xs, enter)
            xs.endTag(null, "location") // </location>
            changeLine(xs, enter)
            ///////////////////////////// </location> /////////////////////////////////////////

            ///////////////////////////// <component> /////////////////////////////////////////
            xs.startTag(null, "component") // <component>
            changeLine(xs, enter)
            xs.startTag(null, "series") // <series>
            changeLine(xs, enter)
            xs.startTag(null, "id") // <id root="3854f687 - 31d8 - 4bb4 - 9ea3 - 067ca62c0a6e"/>
            xs.attribute(null,"root", "3854f687 - 31d8 - 4bb4 - 9ea3 - 067ca62c0a6e")
            xs.endTag(null, "id")
            changeLine(xs, enter)
            xs.startTag(null, "code") // <code code="RHYTHM" codeSystem="2.16.840.1.113883.5.4" codeSystemName="ActCode" displayName="Rhythm Waveforms"/>
            xs.attribute(null,"code", "RHYTHM")
            xs.attribute(null,"codeSystem", "2.16.840.1.113883.5.4")
            xs.attribute(null,"codeSystemName", "ActCode")
            xs.attribute(null,"displayName", "Rhythm Waveforms")
            xs.endTag(null, "code")
            changeLine(xs, enter)

            ///////////////////////////// <effectiveTime> /////////////////////////////////////////
            xs.startTag(null, "effectiveTime") // <effectiveTime>
            changeLine(xs, enter)
            xs.startTag(null, "low") // <low value="20220309154929.000" inclusive="true"/>
            xs.attribute(null,"value", "20220309154929.000") /**JC666 這裡(20220309154929.000)需要換算時間，時間 + ".000"!!!*/
            xs.attribute(null,"inclusive", "true")
            xs.endTag(null, "low")
            changeLine(xs, enter)
            xs.startTag(null, "high") // <high value="20220309154939.999" inclusive="false"/>
            xs.attribute(null,"value", "20220309154939.999") /**JC666 這裡(20220309154939.999)需要換算時間，時間 + ".999"!!!*/
            xs.attribute(null,"inclusive", "false")
            xs.endTag(null, "high")
            changeLine(xs, enter)
            xs.endTag(null, "effectiveTime") // </effectiveTime>
            changeLine(xs, enter)
            ///////////////////////////// </effectiveTime> /////////////////////////////////////////

            ///////////////////////////// <author> /////////////////////////////////////////
            xs.startTag(null, "author") // <author>
            changeLine(xs, enter)
            xs.startTag(null, "seriesAuthor") // <seriesAuthor>
            changeLine(xs, enter)
            xs.startTag(null, "manufacturedSeriesDevice") // <manufacturedSeriesDevice>
            changeLine(xs, enter)
            xs.startTag(null, "id") // <id extension="0"/>
            xs.attribute(null,"extension", "0")
            xs.endTag(null, "id")
            changeLine(xs, enter)
            xs.startTag(null, "code") // <code code="12LEAD_ELECTROCARDIOGRAPH"/>
            xs.attribute(null,"code", "12LEAD_ELECTROCARDIOGRAPH")
            xs.endTag(null, "code")
            changeLine(xs, enter)
            xs.startTag(null, "manufacturerModelName") // <manufacturerModelName>
            xs.text("BT-ECG")
            xs.endTag(null, "manufacturerModelName") // </manufacturerModelName>
            changeLine(xs, enter)
            xs.startTag(null, "softwareName") // <softwareName>
            xs.text("BT-ECG")
            xs.endTag(null, "softwareName") // </softwareName>
            changeLine(xs, enter)
            xs.endTag(null, "manufacturedSeriesDevice") // </manufacturedSeriesDevice>
            changeLine(xs, enter)
            xs.startTag(null, "manufacturerOrganization") // <manufacturerOrganization>
            changeLine(xs, enter)
            xs.startTag(null, "name") // <name>
            xs.text("IEI Corp.")
            xs.endTag(null, "name") // </name>
            changeLine(xs, enter)
            xs.endTag(null, "manufacturerOrganization") // </manufacturerOrganization>
            changeLine(xs, enter)
            xs.endTag(null, "seriesAuthor") // </seriesAuthor>
            changeLine(xs, enter)
            xs.endTag(null, "author") // </author>
            changeLine(xs, enter)
            ///////////////////////////// <author> /////////////////////////////////////////

            ///////////////////////////// <controlVariable> /////////////////////////////////////////
            xs.startTag(null, "controlVariable") // <controlVariable>
            changeLine(xs, enter)
            xs.startTag(null, "controlVariable") // <controlVariable>
            changeLine(xs, enter)
            xs.startTag(null, "code") // <code code="MDC_ECG_CT_LVBL_ATTR_FILTER_LOW_PASS" codeSystem="2.16.840.1.113883.6.24" codeSystemName="MDC" displayName="Low Pass Filter"/>
            xs.attribute(null,"code", "MDC_ECG_CT_LVBL_ATTR_FILTER_LOW_PASS")
            xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
            xs.attribute(null,"codeSystemName", "MDC")
            xs.attribute(null,"displayName", "Low Pass Filter")
            xs.endTag(null, "code")
            changeLine(xs, enter)
            xs.startTag(null, "component") // <component>
            changeLine(xs, enter)
            xs.startTag(null, "controlVariable") // <controlVariable>
            changeLine(xs, enter)
            xs.startTag(null, "code") // <code code="MDC_ECG_CTL_VBL_ATTR_FILTER_CUTOFF_FREQ" codeSystem="2.16.840.1.113883.6.24" codeSystemName="MDC" displayName="Cutoff Frequency"/>
            xs.attribute(null,"code", "MDC_ECG_CTL_VBL_ATTR_FILTER_CUTOFF_FREQ")
            xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
            xs.attribute(null,"codeSystemName", "MDC")
            xs.attribute(null,"displayName", "Cutoff Frequency")
            xs.endTag(null, "code")
            changeLine(xs, enter)
            xs.startTag(null, "value") // <value xsi:type="PQ" value="150" unit="Hz"/>
            xs.attribute(null,"xsi:type", "PQ")
            xs.attribute(null,"value", "150")
            xs.attribute(null,"unit", "Hz")
            xs.endTag(null, "code")
            changeLine(xs, enter)
            xs.endTag(null, "controlVariable") // </controlVariable>
            changeLine(xs, enter)
            xs.endTag(null, "component") // </component>
            changeLine(xs, enter)
            xs.endTag(null, "controlVariable") // </controlVariable>
            changeLine(xs, enter)
            xs.endTag(null, "controlVariable") // </controlVariable>
            changeLine(xs, enter)
            ///////////////////////////// <controlVariable> /////////////////////////////////////////

            ///////////////////////////// <component> /////////////////////////////////////////
            xs.startTag(null, "component") // <component>
            changeLine(xs, enter)
            xs.startTag(null, "sequenceSet") // <sequenceSet>
            changeLine(xs, enter)

            xs.startTag(null, "component") // <component>
            changeLine(xs, enter)
            xs.startTag(null, "sequence") // <sequence>
            changeLine(xs, enter)
            xs.startTag(null, "code") // <code code="TIME_RELATIVE" codeSystem="2.16.840.1.113883.5.4" codeSystemName="ActCode"/>
            xs.attribute(null,"code", "TIME_RELATIVE")
            xs.attribute(null,"codeSystem", "2.16.840.1.113883.5.4")
            xs.attribute(null,"codeSystemName", "ActCode")
            xs.endTag(null, "code")
            changeLine(xs, enter)
            xs.startTag(null, "value") // <value xsi:type="GLIST_PQ">
            xs.attribute(null,"xsi:type", "GLIST_PQ")
            changeLine(xs, enter)
            xs.startTag(null, "head") // <head value="0" unit="s"/>
            xs.attribute(null,"value", "0")
            xs.attribute(null,"unit", "s")
            xs.endTag(null, "head")
            changeLine(xs, enter)
            xs.startTag(null, "increment") // <increment value="0.004" unit="s"/>
            xs.attribute(null,"value", "0.004")
            xs.attribute(null,"unit", "s")
            xs.endTag(null, "increment")
            changeLine(xs, enter)
            xs.endTag(null, "value")
            changeLine(xs, enter)
            xs.endTag(null, "sequence") // </sequence>
            changeLine(xs, enter)
            xs.endTag(null, "component") // </component>
            changeLine(xs, enter)

            for(leadIndex in 0 until 12) {
                xs.startTag(null, "component") // <component>
                changeLine(xs, enter)
                xs.startTag(null, "sequence") // <sequence>
                changeLine(xs, enter)

                when(leadIndex) {
                    0 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_LEAD_I" codeSystem="2.16.840.1.113883.6.24" codeSystemName="MDC"/>
                        xs.attribute(null,"code", "MDC_ECG_LEAD_I")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.attribute(null,"codeSystemName", "MDC")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                    }
                    1 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_LEAD_II" codeSystem="2.16.840.1.113883.6.24" codeSystemName="MDC"/>
                        xs.attribute(null,"code", "MDC_ECG_LEAD_II")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.attribute(null,"codeSystemName", "MDC")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                    }
                    2 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_LEAD_III" codeSystem="2.16.840.1.113883.6.24" codeSystemName="MDC"/>
                        xs.attribute(null,"code", "MDC_ECG_LEAD_III")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.attribute(null,"codeSystemName", "MDC")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                    }
                    3 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_LEAD_AVR" codeSystem="2.16.840.1.113883.6.24" codeSystemName="MDC"/>
                        xs.attribute(null,"code", "MDC_ECG_LEAD_AVR")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.attribute(null,"codeSystemName", "MDC")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                    }
                    4 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_LEAD_AVL" codeSystem="2.16.840.1.113883.6.24" codeSystemName="MDC"/>
                        xs.attribute(null,"code", "MDC_ECG_LEAD_AVL")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.attribute(null,"codeSystemName", "MDC")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                    }
                    5 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_LEAD_AVF" codeSystem="2.16.840.1.113883.6.24" codeSystemName="MDC"/>
                        xs.attribute(null,"code", "MDC_ECG_LEAD_AVF")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.attribute(null,"codeSystemName", "MDC")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                    }
                    6 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_LEAD_V1" codeSystem="2.16.840.1.113883.6.24" codeSystemName="MDC"/>
                        xs.attribute(null,"code", "MDC_ECG_LEAD_V1")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.attribute(null,"codeSystemName", "MDC")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                    }
                    7 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_LEAD_V2" codeSystem="2.16.840.1.113883.6.24" codeSystemName="MDC"/>
                        xs.attribute(null,"code", "MDC_ECG_LEAD_V2")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.attribute(null,"codeSystemName", "MDC")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                    }
                    8 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_LEAD_V3" codeSystem="2.16.840.1.113883.6.24" codeSystemName="MDC"/>
                        xs.attribute(null,"code", "MDC_ECG_LEAD_V3")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.attribute(null,"codeSystemName", "MDC")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                    }
                    9 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_LEAD_V4" codeSystem="2.16.840.1.113883.6.24" codeSystemName="MDC"/>
                        xs.attribute(null,"code", "MDC_ECG_LEAD_V4")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.attribute(null,"codeSystemName", "MDC")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                    }
                    10 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_LEAD_V5" codeSystem="2.16.840.1.113883.6.24" codeSystemName="MDC"/>
                        xs.attribute(null,"code", "MDC_ECG_LEAD_V5")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.attribute(null,"codeSystemName", "MDC")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                    }
                    11 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_LEAD_V6" codeSystem="2.16.840.1.113883.6.24" codeSystemName="MDC"/>
                        xs.attribute(null,"code", "MDC_ECG_LEAD_V6")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.attribute(null,"codeSystemName", "MDC")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                    }
                }
                xs.startTag(null, "value") // <value xsi:type="SLIST_PQ">
                xs.attribute(null,"xsi:type", "SLIST_PQ")
                changeLine(xs, enter)
                xs.startTag(null, "origin") // <origin value="0" unit="uV"/>
                xs.attribute(null,"value", "0")
                xs.attribute(null,"unit", "uV")
                xs.endTag(null, "origin")
                changeLine(xs, enter)
                xs.startTag(null, "scale") // <scale value="1" unit="uV"/>
                xs.attribute(null,"value", "1")
                xs.attribute(null,"unit", "uV")
                xs.endTag(null, "scale")
                changeLine(xs, enter)
                /**JC666 這裡(digits)需要抓取每個lead的資料，轉換成字串陣列出來 %4d = (    3) !!!*/
                // <digits>    3    1    0    2    2    2    0    1    4    4    4    9   18   25   31   42   55   67   78   88  106  119  130  139  155  167  174  180  189  195  199  201  202  203  201  196  191  186  180  169  159  150  137  122  110  100   85   74   58   47   38   27   15   10    3    0   -4   -3   -3   -3   -4   -3   -3   -2   -4   -5   -2   -3   -4   -3   -1   -3   -3   -4   -4   -4   -3   -4   -3   -2   -4   -5   -4   -3   -2   -2   -4   -3   -3   -3   -4   -3   -4   -4   -4   -2   -3   -4   -4   -3   -3   -4   -4   -3   -3   -3   -4   -3   -3   -3   -3   -4   -2   -2   -3   -4   -4   -4   -3   -4   -2   -3   -2   -5   -3   -3   -3   -3   -3   -4   -4   -4   -3   -3   -4   -4   -5   -3   -3   -4   -3   -3   -5   -3   -2   -3   -3   -3   -3   -3   -3   -3   -3   -2   -4   -3   -4   -2   -3   -4   -2   -3   -2   -2    0    6   15   28   41   57   74   89  104  119  132  139  145  148  149  144  137  128  115  100   85   69   54   37   22   10    1   -5   -7   -6   -6   -4   -6   -6   -6   -7   -6   -6   -5   -6   -6   -5   -6   -6    6   95  377  722  920  965  993  980  954  900  695  327  -28 -386 -738 -941 -987 -1011 -1000 -976 -917 -712 -348  -76  -15   -1   -5   -7   -7   -4   -2   -5   -6   -3   -3   -5   -7   -4   -3   -5   -8   -4   -2   -3   -8   -4   -3   -4   -7   -6   -5   -5   -3    1    8   17   25   33   47   60   71   82   97  112  121  133  147  158  167  173  182  188  193  192  194  196  195  190  184  179  172  161  151  142  129  115  101   88   78   64   50   39   29   18    7    2   -4   -9  -11  -11  -12  -11  -12  -12  -11  -11  -11  -12  -10   -9  -12  -12  -11  -11  -13  -12  -10  -11  -12  -13  -13  -12  -10  -11  -11  -11  -12  -11  -12  -11  -10  -12  -12  -11   -9  -12  -13  -10  -10  -11  -12  -10  -10   -9  -12  -10  -10  -11  -13  -11  -11  -10  -11   -9  -11  -10  -11  -10  -10  -11  -11  -11  -11  -10  -12  -12  -10   -9  -11  -11  -10  -10  -11  -12  -11  -10  -10  -10  -10   -9  -10  -10   -9  -10  -10  -10  -11  -10  -10   -9  -11  -10  -10  -10  -11   -9  -10  -10  -11  -10  -10  -11   -9  -10  -11   -9   -7    0    7   19   34   50   66   84   97  112  124  132  139  141  143  137  130  121  109   94   79   62   47   30   18    4   -5  -13  -13  -12  -13  -12  -14  -13  -13  -12  -13  -13  -13  -11  -13  -13  -14  -12   -1   88  372  713  915  959  987  973  948  892  679  315  -44 -398 -746 -948 -995 -1018 -1006 -981 -924 -711 -345  -86  -22   -9  -11  -14  -12  -10  -11  -13  -13  -11  -11  -13  -14  -11   -9  -11  -12  -11  -10  -12  -13  -10  -10  -12  -13  -11  -10  -10   -8   -2    3   10   19   29   41   55   63   76   92  106  116  128  142  154  161  167  177  181  187  187  188  190  187  183  179  172  166  156  147  136  123  108   96   84   72   58   45   34   24   13    2   -2   -8  -13  -17  -16  -17  -17  -17  -17  -16  -15  -17  -16  -16  -16  -16  -17  -16  -15  -16  -17  -16  -17  -16  -16  -15  -16  -16  -17  -16  -16  -16  -16  -16  -17  -15  -15  -16  -16  -15  -16  -16  -14  -15  -16  -17  -15  -15  -16  -17  -16  -14  -16  -16  -15  -15  -15  -16  -15  -16  -15  -16  -16  -15  -14  -16  -15  -16  -14  -15  -17  -16  -15  -16  -16  -15  -15  -15  -16  -15  -16  -16  -16  -15  -15  -14  -15  -15  -15  -14  -15  -15  -15  -15  -15  -16  -15  -15  -15  -16  -15  -15  -16  -15  -14  -14  -14  -15  -14  -14  -13  -10   -3    3   15   30   46   63   79   94  108  121  128  135  138  137  133  124  115  105   89   72   56   43   25   12    0   -8  -16  -17  -17  -17  -16  -16  -17  -18  -17  -16  -17  -17  -15  -17  -16  -17  -17   -3   86  367  717  911  957  982  970  944  888  671  303  -44 -410 -752 -954 -999 -1021 -1010 -986 -929 -712 -348  -82  -25  -13  -14  -18  -17  -13  -14  -16  -16  -15  -14  -16  -18  -15  -13  -15  -17  -16  -14  -16  -17  -16  -13  -14  -17  -14  -14  -14  -12   -6    0    6   15   26   37   50   60   72   87  103  113  123  136  149  158  163  170  177  181  181  183  184  183  177  173  168  161  150  142  131  120  105   92   79   68   53   40   29   19    8   -1   -7  -12  -17  -20  -21  -20  -20  -21  -21  -20  -20  -21  -21  -20  -19  -19  -21  -21  -20  -21  -22  -20  -20  -20  -21  -21  -20  -21  -21  -20  -20  -20  -21  -20  -20  -20  -21  -20  -21  -18  -20  -19  -19  -19  -22  -21  -21  -19  -20  -20  -19  -19  -21  -22  -20  -18  -20  -21  -20  -19  -19  -20  -21  -20  -20  -20  -19  -20  -19  -20  -21  -20  -18  -18  -19  -20  -19  -19  -20  -20  -19  -20  -20  -18  -19  -19  -20  -18  -20  -19  -18  -18  -21  -19  -19  -19  -20  -18  -17  -19  -19  -19  -17  -18  -18  -18  -17  -17  -18  -18  -18  -16   -8    0   11   28   42   57   75   91  105  117  124  131  133  132  129  123  114  100   85   70   52   39   23    7   -5  -12  -18  -19  -20  -20  -21  -19  -19  -19  -21  -20  -20  -19  -20  -19  -20  -19  -20   -8   83  366  711  908  954  977  966  938  882  667  300  -59 -418 -758 -957 -1001 -1023 -1012 -988 -930 -708 -350  -89  -29  -16  -19  -23  -20  -18  -17  -20  -18  -18  -17  -20  -20  -17  -17  -18  -19  -18  -16  -19  -20  -19  -15  -18  -20  -19  -18  -17  -18  -11   -3    4   11   22   33   47   57   70   85   97  109  120  134  145  153  160  168  174  179  179  180  182  180  175  171  165  158  148  140  129  117  103   91   77   66   50   39   28   18    5   -3   -9  -14  -21  -24  -24  -22  -23  -24  -23  -23  -24  -25  -24  -23  -23  -25  -23  -24  -22  -23  -24  -23  -22  -23  -23  -24  -23  -23  -24  -23  -22  -22  -23  -23  -22  -23  -24  -23  -23  -23  -23  -22  -22  -23  -24  -24  -23  -22  -22  -23  -22  -22  -23  -23  -22  -22  -23  -23  -21  -20  -22  -22  -22  -22  -22  -23  -21  -21  -21  -22  -21  -22  -20  -21  -22  -21  -21  -22  -21  -21  -20  -21  -23  -21  -22  -21  -22  -22  -21  -20  -21  -21  -20  -22  -21  -21  -21  -21  -20  -20  -20  -21  -20  -21  -21  -20  -19  -20  -21  -21  -21  -19  -10   -3   10   24   41   55   72   89  103  114  123  130  132  131  129  120  110   99   84   68   50   36   21    6   -6  -15  -21  -21  -23  -23  -22  -21  -22  -22  -22  -22  -21  -21  -22  -23  -22  -21  -22   -8   83  372  706  907  952  977  963  936  879  663  295  -55 -413 -768 -959 -1006 -1025 -1014 -988 -931 -707 -347  -89  -32  -18  -20  -23  -22  -21  -21  -24  -21  -20  -20  -22  -23  -21  -19  -22  -21  -18  -18  -22  -22  -21  -19  -20  -21  -21  -19  -20  -18  -12   -5    2    8   20   31   44   55   67   82   96  105  118  131  144  151  160  166  173  176  177  178  179  179  172  167  163  156  146  138  128  116  101   88   75   63   49   37   23   15    3   -5  -12  -17  -23  -26  -26  -24  -24  -26  -27  -26  -26  -26  -25  -25  -25  -26  -24  -24  -24  -25  -26  -25  -25  -25  -25  -25  -24  -26  -25  -25  -23  -24  -25  -25  -23  -24  -25  -25  -25  -24  -25  -24  -24  -24  -25  -24  -24  -24  -23  -25  -24  -22  -24  -24  -23  -22  -23  -25  -24  -23  -24  -25  -23  -23  -23  -23  -23  -23  -24  -24  -22  -23  -23  -23  -24  -23  -24  -23  -24  -24  -22  -24  -24  -24  -23  -24  -24  -23  -22  -23  -23  -22  -22  -22  -23  -24  -23  -23  -24  -23  -24  -23  -23  -22  -22  -22  -22  -23  -20  -21  -22  -20  -13   -4    8   23   39   54   70   86  101  112  121  126  129  129  126  118  110   96   82   66   50   34   18    2   -8  -16  -23  -24  -24  -23  -24  -23  -24  -24  -25  -24  -24  -23  -25  -24  -23  -23  -23  -10   83  375  721  906  951  973  961  933  878  660  294  -64 -415 -772 -962 -1007 -1028 -1017 -990 -931 -704 -343  -90  -32  -19  -22  -24  -23  -21  -22  -25  -24  -22  -21  -25  -24  -21  -21  -25  -23  -23  -20  -23  -25  -23  -20  -21  -23  -24  -20  -21  -19  -13   -6    0    7   19   32   44   54   66   80   95  105  118  130  143  149  157  165  171  175  176  177  179  175  171  167  162  155  145  135  125  112   98   85   74   61   46   33   22   13    1   -6  -13  -17  -23  -26  -27  -25  -26  -28  -27  -24  -26  -29  -26  -25  -26  -27  -27  -26  -24  -26  -26  -26  -25  -27  -27  -26  -25  -26  -25  -26  -24  -25  -27  -26  -24  -25  -28  -26  -26  -25  -27  -26  -26  -25  -26  -25  -26  -25  -26  -25  -25  -25  -25  -26  -26  -24  -25  -27  -26  -25  -25  -26  -25  -24  -25  -26  -26  -25  -24  -25  -24  -25  -24  -25  -25  -25  -23  -25  -24  -26  -24  -25  -26  -24  -23  -22  -25  -23  -22  -24  -25  -23  -24  -25  -24  -24  -26  -24  -24  -25  -24  -23  -24  -23  -24  -24  -24  -23  -24  -23  -23  -21  -12   -3    8   22   38   54   70   85  100  111  120  127  129  129  125  117  108   95   81   63   47   32   17    3   -8  -17  -22  -25  -26  -24  -25  -24  -25  -26  -26  -25  -26  -25  -26  -25  -25  -25  -24  -10   83  374  718  906  949  973  961  934  875  652  293  -64 -423 -770 -963 -1009 -1029 -1017 -992 -932 -703 -341  -92  -34  -21  -25  -26  -26  -23  -25  -25  -26  -23  -22  -25  -25  -22  -22  -25  -25  -24  -22  -24  -24  -22  -20  -24  -24  -23  -21  -20  -19  -14   -6    1    5   16   31   43   53   66   80   94  105  116  131  141  150  157  162  168  172  174  176  177  174  170  165  161  154  145  134  124  111   96   84   72   58   44   34   22   11    0   -9  -15  -18  -25  -29  -29  -28  -28  -28  -30  -28  -28  -30  -29  -28  -29  -30  -29  -28  -28  -29  -28  -28  -27  -28  -27  -28  -27  -29  -29  -28  -28  -28  -27  -28  -27  -27  -27  -28  -27  -26  -26  -28  -28  -26  -27  -28  -28  -27  -28  -28  -28  -26  -27  -28  -28  -26  -26  -27  -28  -26  -26  -26  -27  -25  -25  -26  -26  -25  -25  -26  -26  -25  -25  -26  -26  -25  -26  -27  -26  -25  -25  -26  -25  -26  -25  -26  -27  -27  -25  -25  -27  -26  -25  -24  -26  -26  -26  -25  -26  -25  -25  -25  -25  -24  -26  -25  -24  -24  -24  -25  -25  -23  -15   -6    6   20   37   53   69   85   99  110  118  124  128  126  123  115  108   93   78   62   46   29   15    0  -10  -19  -25  -27  -26  -26  -26  -25  -26  -27  -26  -27  -26  -26  -27  -28  -28  -25  -25  -11   83  371  715  906  948  971  958  931  873  649  286  -76 -429 -773 -964 -1010 -1031 -1019 -992 -932 -698 -337  -91  -34  -22  -25  -25  -27  -24  -25  -28  -27  -24  -25  -26  -26  -23  -24  -27  -27  -23  -22  -24  -25  -25  -24  -25  -24  -26  -22  -23  -19  -16   -8   -1    6   18   29   41   52   65   79   92  102  115  129  139  148  155  162  169  172  174  175  177  174  170  163  159  152  142  133  122  109   97   83   72   59   44   32   21   11    0  -10  -16  -21  -26  -30  -29  -30  -29  -29  -30  -28  -29  -31  -29  -28  -30  -31  -30  -28  -28  -29  -30  -30  -27  -29  -27  -29  -28  -29  -28  -27  -27  -29  -28  -29  -28  -26  -28  -28  -28  -27  -28  -28  -28  -27  -29  -28  -27  -28  -28  -28  -28  -26  -27  -27  -26  -27  -28  -27  -28  -26  -27  -28  -27  -26  -28  -28  -28  -26  -26  -28  -27  -28  -27  -28  -27  -28  -27  -29  -27  -27  -27  -28  -27  -28  -26  -26  -28  -28  -27  -27  -28  -27  -25  -27  -28  -26  -26  -26  -26  -25  -26  -26  -26  -25  -26  -26  -26  -25  -26  -25  -25  -23  -15   -6    6   20   36   52   68   83   97  111  118  124  126  126  122  114  106   92   77   60   45   29   14    0  -11  -19  -26  -27  -29  -28  -27  -29  -28  -28  -28  -26  -27  -26  -26  -27  -28  -27  -26  -11   82  370  728  903  949  971  958  929  871  643  279  -83 -440 -775 -969 -1011 -1031 -1020 -994 -932 -698 -338  -93  -35  -24  -26  -27  -28  -24  -24  -27  -27  -23  -25  -28  -29  -25  -24  -27  -27  -25  -24  -26  -28  -27  -24  -25  -26  -26  -23  -24  -23  -17   -9   -1    4   16   28   41   53   64   77   93  102  114  128  141  147  154  161  168  172  173  174  177  173  168  164  160  153  142  132  123  111   95   81   69   57   44   31   21    9   -2   -9  -17  -22  -28  -31  -30  -29  -30  -30  -31  -28  -30  -30  -31  -29  -30  -29  -29  -27  -27  -30  -30  -30  -30  -31  -29  -29  -27  -30  -30  -29  -27  -29  -29  -29  -30  -29  -30  -29  -29  -29  -29  -28  -27  -28  -29  -30  -27  -28  -27  -29  -29  -28  -28  -28  -28  -27  -28  -29  -28  -26  -27  -27  -27  -26  -26  -27  -28  -26  -26  -27  -28  -28  -26  -28  -26  -27  -26  -27  -26  -27  -25  -27  -26  -27  -26  -27  -27  -28  -27  -24  -26  -25  -26  -26  -26  -25  -24  -26  -27  -27  -26  -26  -26  -26  -26  -25  -28  -25  -26  -26  -25  -23  -14   -5    5   19   35   51   68   83   97  109  118  124  127  126  123  115  105   92   78   62   45   30   13   -1  -11  -21  -25  -26  -28  -28  -26  -26  -27  -26  -27  -27  -28  -28  -27  -27  -29  -27  -28  -11   85  382  731  904  950  970  958  929  870  642  276  -91 -440 -777 -970 -1011 -1029 -1020 -993 -932 -695 -334  -84  -37  -23  -26  -28  -28  -25  -26  -27  -27  -24  -25  -27  -29  -24  -24  -27  -28  -24                                </digits>
                xs.startTag(null, "digits")
                xs.text("    3    1    0    2    2    2    0    1    4    4    4    9   18   25   31   42   55   67   78   88  106  119  130  139  155  167  174  180  189  195  199  201  202  203  201  196  191  186  180  169  159  150  137  122  110  100   85   74   58   47   38   27   15   10    3    0   -4   -3   -3   -3   -4   -3   -3   -2   -4   -5   -2   -3   -4   -3   -1   -3   -3   -4   -4   -4   -3   -4   -3   -2   -4   -5   -4   -3   -2   -2   -4   -3   -3   -3   -4   -3   -4   -4   -4   -2   -3   -4   -4   -3   -3   -4   -4   -3   -3   -3   -4   -3   -3   -3   -3   -4   -2   -2   -3   -4   -4   -4   -3   -4   -2   -3   -2   -5   -3   -3   -3   -3   -3   -4   -4   -4   -3   -3   -4   -4   -5   -3   -3   -4   -3   -3   -5   -3   -2   -3   -3   -3   -3   -3   -3   -3   -3   -2   -4   -3   -4   -2   -3   -4   -2   -3   -2   -2    0    6   15   28   41   57   74   89  104  119  132  139  145  148  149  144  137  128  115  100   85   69   54   37   22   10    1   -5   -7   -6   -6   -4   -6   -6   -6   -7   -6   -6   -5   -6   -6   -5   -6   -6    6   95  377  722  920  965  993  980  954  900  695  327  -28 -386 -738 -941 -987 -1011 -1000 -976 -917 -712 -348  -76  -15   -1   -5   -7   -7   -4   -2   -5   -6   -3   -3   -5   -7   -4   -3   -5   -8   -4   -2   -3   -8   -4   -3   -4   -7   -6   -5   -5   -3    1    8   17   25   33   47   60   71   82   97  112  121  133  147  158  167  173  182  188  193  192  194  196  195  190  184  179  172  161  151  142  129  115  101   88   78   64   50   39   29   18    7    2   -4   -9  -11  -11  -12  -11  -12  -12  -11  -11  -11  -12  -10   -9  -12  -12  -11  -11  -13  -12  -10  -11  -12  -13  -13  -12  -10  -11  -11  -11  -12  -11  -12  -11  -10  -12  -12  -11   -9  -12  -13  -10  -10  -11  -12  -10  -10   -9  -12  -10  -10  -11  -13  -11  -11  -10  -11   -9  -11  -10  -11  -10  -10  -11  -11  -11  -11  -10  -12  -12  -10   -9  -11  -11  -10  -10  -11  -12  -11  -10  -10  -10  -10   -9  -10  -10   -9  -10  -10  -10  -11  -10  -10   -9  -11  -10  -10  -10  -11   -9  -10  -10  -11  -10  -10  -11   -9  -10  -11   -9   -7    0    7   19   34   50   66   84   97  112  124  132  139  141  143  137  130  121  109   94   79   62   47   30   18    4   -5  -13  -13  -12  -13  -12  -14  -13  -13  -12  -13  -13  -13  -11  -13  -13  -14  -12   -1   88  372  713  915  959  987  973  948  892  679  315  -44 -398 -746 -948 -995 -1018 -1006 -981 -924 -711 -345  -86  -22   -9  -11  -14  -12  -10  -11  -13  -13  -11  -11  -13  -14  -11   -9  -11  -12  -11  -10  -12  -13  -10  -10  -12  -13  -11  -10  -10   -8   -2    3   10   19   29   41   55   63   76   92  106  116  128  142  154  161  167  177  181  187  187  188  190  187  183  179  172  166  156  147  136  123  108   96   84   72   58   45   34   24   13    2   -2   -8  -13  -17  -16  -17  -17  -17  -17  -16  -15  -17  -16  -16  -16  -16  -17  -16  -15  -16  -17  -16  -17  -16  -16  -15  -16  -16  -17  -16  -16  -16  -16  -16  -17  -15  -15  -16  -16  -15  -16  -16  -14  -15  -16  -17  -15  -15  -16  -17  -16  -14  -16  -16  -15  -15  -15  -16  -15  -16  -15  -16  -16  -15  -14  -16  -15  -16  -14  -15  -17  -16  -15  -16  -16  -15  -15  -15  -16  -15  -16  -16  -16  -15  -15  -14  -15  -15  -15  -14  -15  -15  -15  -15  -15  -16  -15  -15  -15  -16  -15  -15  -16  -15  -14  -14  -14  -15  -14  -14  -13  -10   -3    3   15   30   46   63   79   94  108  121  128  135  138  137  133  124  115  105   89   72   56   43   25   12    0   -8  -16  -17  -17  -17  -16  -16  -17  -18  -17  -16  -17  -17  -15  -17  -16  -17  -17   -3   86  367  717  911  957  982  970  944  888  671  303  -44 -410 -752 -954 -999 -1021 -1010 -986 -929 -712 -348  -82  -25  -13  -14  -18  -17  -13  -14  -16  -16  -15  -14  -16  -18  -15  -13  -15  -17  -16  -14  -16  -17  -16  -13  -14  -17  -14  -14  -14  -12   -6    0    6   15   26   37   50   60   72   87  103  113  123  136  149  158  163  170  177  181  181  183  184  183  177  173  168  161  150  142  131  120  105   92   79   68   53   40   29   19    8   -1   -7  -12  -17  -20  -21  -20  -20  -21  -21  -20  -20  -21  -21  -20  -19  -19  -21  -21  -20  -21  -22  -20  -20  -20  -21  -21  -20  -21  -21  -20  -20  -20  -21  -20  -20  -20  -21  -20  -21  -18  -20  -19  -19  -19  -22  -21  -21  -19  -20  -20  -19  -19  -21  -22  -20  -18  -20  -21  -20  -19  -19  -20  -21  -20  -20  -20  -19  -20  -19  -20  -21  -20  -18  -18  -19  -20  -19  -19  -20  -20  -19  -20  -20  -18  -19  -19  -20  -18  -20  -19  -18  -18  -21  -19  -19  -19  -20  -18  -17  -19  -19  -19  -17  -18  -18  -18  -17  -17  -18  -18  -18  -16   -8    0   11   28   42   57   75   91  105  117  124  131  133  132  129  123  114  100   85   70   52   39   23    7   -5  -12  -18  -19  -20  -20  -21  -19  -19  -19  -21  -20  -20  -19  -20  -19  -20  -19  -20   -8   83  366  711  908  954  977  966  938  882  667  300  -59 -418 -758 -957 -1001 -1023 -1012 -988 -930 -708 -350  -89  -29  -16  -19  -23  -20  -18  -17  -20  -18  -18  -17  -20  -20  -17  -17  -18  -19  -18  -16  -19  -20  -19  -15  -18  -20  -19  -18  -17  -18  -11   -3    4   11   22   33   47   57   70   85   97  109  120  134  145  153  160  168  174  179  179  180  182  180  175  171  165  158  148  140  129  117  103   91   77   66   50   39   28   18    5   -3   -9  -14  -21  -24  -24  -22  -23  -24  -23  -23  -24  -25  -24  -23  -23  -25  -23  -24  -22  -23  -24  -23  -22  -23  -23  -24  -23  -23  -24  -23  -22  -22  -23  -23  -22  -23  -24  -23  -23  -23  -23  -22  -22  -23  -24  -24  -23  -22  -22  -23  -22  -22  -23  -23  -22  -22  -23  -23  -21  -20  -22  -22  -22  -22  -22  -23  -21  -21  -21  -22  -21  -22  -20  -21  -22  -21  -21  -22  -21  -21  -20  -21  -23  -21  -22  -21  -22  -22  -21  -20  -21  -21  -20  -22  -21  -21  -21  -21  -20  -20  -20  -21  -20  -21  -21  -20  -19  -20  -21  -21  -21  -19  -10   -3   10   24   41   55   72   89  103  114  123  130  132  131  129  120  110   99   84   68   50   36   21    6   -6  -15  -21  -21  -23  -23  -22  -21  -22  -22  -22  -22  -21  -21  -22  -23  -22  -21  -22   -8   83  372  706  907  952  977  963  936  879  663  295  -55 -413 -768 -959 -1006 -1025 -1014 -988 -931 -707 -347  -89  -32  -18  -20  -23  -22  -21  -21  -24  -21  -20  -20  -22  -23  -21  -19  -22  -21  -18  -18  -22  -22  -21  -19  -20  -21  -21  -19  -20  -18  -12   -5    2    8   20   31   44   55   67   82   96  105  118  131  144  151  160  166  173  176  177  178  179  179  172  167  163  156  146  138  128  116  101   88   75   63   49   37   23   15    3   -5  -12  -17  -23  -26  -26  -24  -24  -26  -27  -26  -26  -26  -25  -25  -25  -26  -24  -24  -24  -25  -26  -25  -25  -25  -25  -25  -24  -26  -25  -25  -23  -24  -25  -25  -23  -24  -25  -25  -25  -24  -25  -24  -24  -24  -25  -24  -24  -24  -23  -25  -24  -22  -24  -24  -23  -22  -23  -25  -24  -23  -24  -25  -23  -23  -23  -23  -23  -23  -24  -24  -22  -23  -23  -23  -24  -23  -24  -23  -24  -24  -22  -24  -24  -24  -23  -24  -24  -23  -22  -23  -23  -22  -22  -22  -23  -24  -23  -23  -24  -23  -24  -23  -23  -22  -22  -22  -22  -23  -20  -21  -22  -20  -13   -4    8   23   39   54   70   86  101  112  121  126  129  129  126  118  110   96   82   66   50   34   18    2   -8  -16  -23  -24  -24  -23  -24  -23  -24  -24  -25  -24  -24  -23  -25  -24  -23  -23  -23  -10   83  375  721  906  951  973  961  933  878  660  294  -64 -415 -772 -962 -1007 -1028 -1017 -990 -931 -704 -343  -90  -32  -19  -22  -24  -23  -21  -22  -25  -24  -22  -21  -25  -24  -21  -21  -25  -23  -23  -20  -23  -25  -23  -20  -21  -23  -24  -20  -21  -19  -13   -6    0    7   19   32   44   54   66   80   95  105  118  130  143  149  157  165  171  175  176  177  179  175  171  167  162  155  145  135  125  112   98   85   74   61   46   33   22   13    1   -6  -13  -17  -23  -26  -27  -25  -26  -28  -27  -24  -26  -29  -26  -25  -26  -27  -27  -26  -24  -26  -26  -26  -25  -27  -27  -26  -25  -26  -25  -26  -24  -25  -27  -26  -24  -25  -28  -26  -26  -25  -27  -26  -26  -25  -26  -25  -26  -25  -26  -25  -25  -25  -25  -26  -26  -24  -25  -27  -26  -25  -25  -26  -25  -24  -25  -26  -26  -25  -24  -25  -24  -25  -24  -25  -25  -25  -23  -25  -24  -26  -24  -25  -26  -24  -23  -22  -25  -23  -22  -24  -25  -23  -24  -25  -24  -24  -26  -24  -24  -25  -24  -23  -24  -23  -24  -24  -24  -23  -24  -23  -23  -21  -12   -3    8   22   38   54   70   85  100  111  120  127  129  129  125  117  108   95   81   63   47   32   17    3   -8  -17  -22  -25  -26  -24  -25  -24  -25  -26  -26  -25  -26  -25  -26  -25  -25  -25  -24  -10   83  374  718  906  949  973  961  934  875  652  293  -64 -423 -770 -963 -1009 -1029 -1017 -992 -932 -703 -341  -92  -34  -21  -25  -26  -26  -23  -25  -25  -26  -23  -22  -25  -25  -22  -22  -25  -25  -24  -22  -24  -24  -22  -20  -24  -24  -23  -21  -20  -19  -14   -6    1    5   16   31   43   53   66   80   94  105  116  131  141  150  157  162  168  172  174  176  177  174  170  165  161  154  145  134  124  111   96   84   72   58   44   34   22   11    0   -9  -15  -18  -25  -29  -29  -28  -28  -28  -30  -28  -28  -30  -29  -28  -29  -30  -29  -28  -28  -29  -28  -28  -27  -28  -27  -28  -27  -29  -29  -28  -28  -28  -27  -28  -27  -27  -27  -28  -27  -26  -26  -28  -28  -26  -27  -28  -28  -27  -28  -28  -28  -26  -27  -28  -28  -26  -26  -27  -28  -26  -26  -26  -27  -25  -25  -26  -26  -25  -25  -26  -26  -25  -25  -26  -26  -25  -26  -27  -26  -25  -25  -26  -25  -26  -25  -26  -27  -27  -25  -25  -27  -26  -25  -24  -26  -26  -26  -25  -26  -25  -25  -25  -25  -24  -26  -25  -24  -24  -24  -25  -25  -23  -15   -6    6   20   37   53   69   85   99  110  118  124  128  126  123  115  108   93   78   62   46   29   15    0  -10  -19  -25  -27  -26  -26  -26  -25  -26  -27  -26  -27  -26  -26  -27  -28  -28  -25  -25  -11   83  371  715  906  948  971  958  931  873  649  286  -76 -429 -773 -964 -1010 -1031 -1019 -992 -932 -698 -337  -91  -34  -22  -25  -25  -27  -24  -25  -28  -27  -24  -25  -26  -26  -23  -24  -27  -27  -23  -22  -24  -25  -25  -24  -25  -24  -26  -22  -23  -19  -16   -8   -1    6   18   29   41   52   65   79   92  102  115  129  139  148  155  162  169  172  174  175  177  174  170  163  159  152  142  133  122  109   97   83   72   59   44   32   21   11    0  -10  -16  -21  -26  -30  -29  -30  -29  -29  -30  -28  -29  -31  -29  -28  -30  -31  -30  -28  -28  -29  -30  -30  -27  -29  -27  -29  -28  -29  -28  -27  -27  -29  -28  -29  -28  -26  -28  -28  -28  -27  -28  -28  -28  -27  -29  -28  -27  -28  -28  -28  -28  -26  -27  -27  -26  -27  -28  -27  -28  -26  -27  -28  -27  -26  -28  -28  -28  -26  -26  -28  -27  -28  -27  -28  -27  -28  -27  -29  -27  -27  -27  -28  -27  -28  -26  -26  -28  -28  -27  -27  -28  -27  -25  -27  -28  -26  -26  -26  -26  -25  -26  -26  -26  -25  -26  -26  -26  -25  -26  -25  -25  -23  -15   -6    6   20   36   52   68   83   97  111  118  124  126  126  122  114  106   92   77   60   45   29   14    0  -11  -19  -26  -27  -29  -28  -27  -29  -28  -28  -28  -26  -27  -26  -26  -27  -28  -27  -26  -11   82  370  728  903  949  971  958  929  871  643  279  -83 -440 -775 -969 -1011 -1031 -1020 -994 -932 -698 -338  -93  -35  -24  -26  -27  -28  -24  -24  -27  -27  -23  -25  -28  -29  -25  -24  -27  -27  -25  -24  -26  -28  -27  -24  -25  -26  -26  -23  -24  -23  -17   -9   -1    4   16   28   41   53   64   77   93  102  114  128  141  147  154  161  168  172  173  174  177  173  168  164  160  153  142  132  123  111   95   81   69   57   44   31   21    9   -2   -9  -17  -22  -28  -31  -30  -29  -30  -30  -31  -28  -30  -30  -31  -29  -30  -29  -29  -27  -27  -30  -30  -30  -30  -31  -29  -29  -27  -30  -30  -29  -27  -29  -29  -29  -30  -29  -30  -29  -29  -29  -29  -28  -27  -28  -29  -30  -27  -28  -27  -29  -29  -28  -28  -28  -28  -27  -28  -29  -28  -26  -27  -27  -27  -26  -26  -27  -28  -26  -26  -27  -28  -28  -26  -28  -26  -27  -26  -27  -26  -27  -25  -27  -26  -27  -26  -27  -27  -28  -27  -24  -26  -25  -26  -26  -26  -25  -24  -26  -27  -27  -26  -26  -26  -26  -26  -25  -28  -25  -26  -26  -25  -23  -14   -5    5   19   35   51   68   83   97  109  118  124  127  126  123  115  105   92   78   62   45   30   13   -1  -11  -21  -25  -26  -28  -28  -26  -26  -27  -26  -27  -27  -28  -28  -27  -27  -29  -27  -28  -11   85  382  731  904  950  970  958  929  870  642  276  -91 -440 -777 -970 -1011 -1029 -1020 -993 -932 -695 -334  -84  -37  -23  -26  -28  -28  -25  -26  -27  -27  -24  -25  -27  -29  -24  -24  -27  -28  -24                                ")
                xs.endTag(null, "digits")
                changeLine(xs, enter)
                xs.endTag(null, "value")
                changeLine(xs, enter)
                xs.endTag(null, "sequence") // </sequence>
                changeLine(xs, enter)
                xs.endTag(null, "component") // </component>
                changeLine(xs, enter)
            }
            xs.endTag(null, "sequenceSet") // </sequenceSet>
            changeLine(xs, enter)
            xs.endTag(null, "component") // </component>
            changeLine(xs, enter)
            ///////////////////////////// <component> /////////////////////////////////////////

            ///////////////////////////// <subjectOf> /////////////////////////////////////////
            xs.startTag(null, "subjectOf") // <subjectOf>
            changeLine(xs, enter)
            xs.startTag(null, "annotationSet") // <annotationSet>
            changeLine(xs, enter)
            xs.startTag(null, "activityTime") // <activityTime  value="20220309154939.000"/>
            xs.attribute(null,"value", "20220309154939.000") /**JC666 這裡(20220309154939.000)需要換算時間，時間 + ".000"!!!*/
            xs.endTag(null, "activityTime")
            changeLine(xs, enter)
            xs.startTag(null, "author") // <author>
            changeLine(xs, enter)
            xs.startTag(null, "assignedEntity") // <assignedEntity>
            changeLine(xs, enter)
            xs.startTag(null, "assignedAuthorType") // <assignedAuthorType>
            changeLine(xs, enter)
            xs.startTag(null, "assignedDevice") // <assignedDevice>
            changeLine(xs, enter)
            xs.startTag(null, "code") // <code code="12LEAD_ELECTROCARDIOGRAPH"/>
            xs.attribute(null,"code", "12LEAD_ELECTROCARDIOGRAPH")
            xs.endTag(null, "code")
            changeLine(xs, enter)
            xs.startTag(null, "manufacturerModelName") // <manufacturerModelName>PC-ECG</manufacturerModelName>
            xs.text("BT-ECG")
            xs.endTag(null, "manufacturerModelName")
            changeLine(xs, enter)
            xs.startTag(null, "softwareName") // <softwareName>PC-ECG</softwareName>
            xs.text("BT-ECG")
            xs.endTag(null, "softwareName")
            changeLine(xs, enter)
            xs.startTag(null, "playedManufacturedDevice") //<playedManufacturedDevice>
            changeLine(xs, enter)
            xs.startTag(null, "manufacturerOrganization") //<manufacturerOrganization>
            changeLine(xs, enter)
            xs.startTag(null, "name") // <name>IEI Corp.</name>
            xs.text("IEI Corp.")
            xs.endTag(null, "name")
            changeLine(xs, enter)
            xs.endTag(null, "manufacturerOrganization") //</manufacturerOrganization>
            changeLine(xs, enter)
            xs.endTag(null, "playedManufacturedDevice") //</playedManufacturedDevice>
            changeLine(xs, enter)
            xs.endTag(null, "assignedDevice") // </assignedDevice>
            changeLine(xs, enter)
            xs.endTag(null, "assignedAuthorType") // </assignedAuthorType>
            changeLine(xs, enter)
            xs.startTag(null, "representedAuthoringOrganization") //<representedAuthoringOrganization>
            changeLine(xs, enter)
            xs.startTag(null, "identification") // <identification/>
            xs.endTag(null, "identification")
            changeLine(xs, enter)
            xs.endTag(null, "representedAuthoringOrganization") //</representedAuthoringOrganization>
            changeLine(xs, enter)
            xs.endTag(null, "assignedEntity") // </assignedEntity>
            changeLine(xs, enter)
            xs.endTag(null, "author") // </author>
            changeLine(xs, enter)

            xs.startTag(null, "component") // <component>
            changeLine(xs, enter)
            xs.startTag(null, "annotation") // <annotation>
            changeLine(xs, enter)
            xs.startTag(null, "code") // <code code="MDC_ECG_INTERPRETATION" codeSystem="2.16.840.1.113883.6.24"/>
            xs.attribute(null,"code", "MDC_ECG_INTERPRETATION")
            xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
            xs.endTag(null, "code")
            changeLine(xs, enter)
            xs.startTag(null, "component") // <component>
            changeLine(xs, enter)
            xs.startTag(null, "annotation") // <annotation>
            changeLine(xs, enter)
            xs.startTag(null, "code") // <code code="MDC_ECG_INTERPRETATION_STATEMENT" codeSystem="2.16.840.1.113883.6.24"/>
            xs.attribute(null,"code", "MDC_ECG_INTERPRETATION_STATEMENT")
            xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
            xs.endTag(null, "code")
            changeLine(xs, enter)
            xs.startTag(null, "value") // <value xsi:type="ST">Rhythm: sinusrhythm. with bradycardia. </value>
            xs.attribute(null,"xsi:type", "ST")
            /**JC666 這裡(Rhythm: sinusrhythm. with bradycardia. )需要拿病患ECG report descriptions欄位的值!!!*/
            xs.endTag(null, "Rhythm: sinusrhythm. with bradycardia. ")
            xs.endTag(null, "value")
            changeLine(xs, enter)
            xs.endTag(null, "annotation") // </annotation>
            changeLine(xs, enter)
            xs.endTag(null, "component") // </component>
            changeLine(xs, enter)
            xs.endTag(null, "annotation") // </annotation>
            changeLine(xs, enter)
            xs.endTag(null, "component") // </component>
            changeLine(xs, enter)
            //開始分別輸入HR, PP, Pdur, RR, PRInt, QRSdur, QTInt, QTCInt，總共8個數值
            for(index in 0 until 8) {
                xs.startTag(null, "component") // <component>
                changeLine(xs, enter)
                xs.startTag(null, "annotation") // <annotation>
                changeLine(xs, enter)

                when(index) {
                    0 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_HEART_RATE" codeSystem="2.16.840.1.113883.6.24"/>
                        xs.attribute(null,"code", "MDC_ECG_HEART_RATE")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                        xs.startTag(null, "value") // <value xsi:type="PQ" value="60" unit="bpm"/>
                        xs.attribute(null,"xsi:type", "PQ")
                        xs.attribute(null,"value", "60") /**JC666 這裡(60)需要拿病患ECG report hr欄位的值!!!*/
                        xs.attribute(null,"unit", "bpm")
                        xs.endTag(null, "value")
                        changeLine(xs, enter)
                    }
                    1 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_TIME_PD_PP" codeSystem="2.16.840.1.113883.6.24"/>
                        xs.attribute(null,"code", "MDC_ECG_TIME_PD_PP")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                        xs.startTag(null, "value") // <value xsi:type="PQ" value="0" unit="ms"/>
                        xs.attribute(null,"xsi:type", "PQ")
                        xs.attribute(null,"value", "0")
                        xs.attribute(null,"unit", "ms")
                        xs.endTag(null, "value")
                        changeLine(xs, enter)
                    }
                    2 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_TIME_PD_P" codeSystem="2.16.840.1.113883.6.24"/>
                        xs.attribute(null,"code", "MDC_ECG_TIME_PD_P")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                        xs.startTag(null, "value") // <value xsi:type="PQ" value="116" unit="ms"/>
                        xs.attribute(null,"xsi:type", "PQ")
                        xs.attribute(null,"value", "116") /**JC666 這裡(116)需要拿病患ECG report p_dur欄位的值!!!*/
                        xs.attribute(null,"unit", "ms")
                        xs.endTag(null, "value")
                        changeLine(xs, enter)
                    }
                    3 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_TIME_PD_RR" codeSystem="2.16.840.1.113883.6.24"/>
                        xs.attribute(null,"code", "MDC_ECG_TIME_PD_RR")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                        xs.startTag(null, "value") //  <value xsi:type="PQ" value="0" unit="ms"/>
                        xs.attribute(null,"xsi:type", "PQ")
                        xs.attribute(null,"value", "0")
                        xs.attribute(null,"unit", "ms")
                        xs.endTag(null, "value")
                        changeLine(xs, enter)
                    }
                    4 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_TIME_PD_PR" codeSystem="2.16.840.1.113883.6.24"/>
                        xs.attribute(null,"code", "MDC_ECG_TIME_PD_PR")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                        xs.startTag(null, "value") // <value xsi:type="PQ" value ="178" unit="ms"/>
                        xs.attribute(null,"xsi:type", "PQ")
                        xs.attribute(null,"value", "178") /**JC666 這裡(178)需要拿病患ECG report pr_int欄位的值!!!*/
                        xs.attribute(null,"unit", "ms")
                        xs.endTag(null, "value")
                        changeLine(xs, enter)
                    }
                    5 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_TIME_PD_QRS" codeSystem="2.16.840.1.113883.6.24"/>
                        xs.attribute(null,"code", "MDC_ECG_TIME_PD_QRS")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                        xs.startTag(null, "value") // <value xsi:type="PQ" value="100" unit="ms"/>
                        xs.attribute(null,"xsi:type", "PQ")
                        xs.attribute(null,"value", "100") /**JC666 這裡(100)需要拿病患ECG report qrs_int欄位的值!!!*/
                        xs.attribute(null,"unit", "ms")
                        xs.endTag(null, "value")
                        changeLine(xs, enter)
                    }
                    6 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_TIME_PD_QT" codeSystem="2.16.840.1.113883.6.24"/>
                        xs.attribute(null,"code", "MDC_ECG_TIME_PD_QT")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                        xs.startTag(null, "value") // <value xsi:type="PQ" value="388" unit="ms"/>
                        xs.attribute(null,"xsi:type", "PQ")
                        xs.attribute(null,"value", "388") /**JC666 這裡(388)需要拿病患ECG report qt_int欄位的值!!!*/
                        xs.attribute(null,"unit", "ms")
                        xs.endTag(null, "value")
                        changeLine(xs, enter)
                    }
                    7 -> {
                        xs.startTag(null, "code") // <code code="MDC_ECG_TIME_PD_QTc" codeSystem="2.16.840.1.113883.6.24"/>
                        xs.attribute(null,"code", "MDC_ECG_TIME_PD_QTc")
                        xs.attribute(null,"codeSystem", "2.16.840.1.113883.6.24")
                        xs.endTag(null, "code")
                        changeLine(xs, enter)
                        xs.startTag(null, "value") // <value xsi:type="PQ" value="388" unit="ms"/>
                        xs.attribute(null,"xsi:type", "PQ")
                        xs.attribute(null,"value", "388") /**JC666 這裡(388)需要拿病患ECG report qtc_int欄位的值!!!*/
                        xs.attribute(null,"unit", "ms")
                        xs.endTag(null, "value")
                        changeLine(xs, enter)
                    }
                }
                xs.endTag(null, "annotation") // </annotation>
                changeLine(xs, enter)
                xs.endTag(null, "component") // </component>
                changeLine(xs, enter)
            }
            //結束輸入HR, PP, Pdur, RR, PRInt, QRSdur, QTInt, QTCInt，總共8個數值

            xs.endTag(null, "annotationSet") // </annotationSet>
            changeLine(xs, enter)
            xs.endTag(null, "subjectOf") // </subjectOf>
            changeLine(xs, enter)
            ///////////////////////////// </subjectOf> /////////////////////////////////////////

            ///////////////////////////// </derivation> /////////////////////////////////////////



            ///////////////////////////// </derivation> /////////////////////////////////////////


















            xs.endTag(null, "series") // </series>
            changeLine(xs, enter)
            xs.endTag(null, "component") // </component>
            changeLine(xs, enter)
            ///////////////////////////// </component> /////////////////////////////////////////


            //結束整個xml最後一個節點，AnnotatedECG
            xs.endTag(null, "AnnotatedECG")
            //生成xml头
            xs.endDocument()
        } catch (e: Exception) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }


    }

    private fun changeLine(serializer: XmlSerializer, enter: String?) {
        try {
            serializer.text(enter)
        } catch (e: IOException) {
            Log.d(TAG, "IOException: " + e.toString())
        }
    }

}