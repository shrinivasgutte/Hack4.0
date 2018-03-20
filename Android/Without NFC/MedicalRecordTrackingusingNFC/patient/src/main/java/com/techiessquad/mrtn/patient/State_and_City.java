package com.techiessquad.mrtn.patient;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajits on 13-12-2017.
 */

public class State_and_City {

   //static String url="http://192.168.43.190/MRTS/patient/";

    // public static String url="http://192.168.43.130/MRTS/patient/";
  //  public static String url_patient_report="http://192.168.43.130/MRTS/User/";

      public static String url="http://asdeveloper.ddns.net/MRTS/patient/";
      public static String url_patient_report="http://asdeveloper.ddns.net/MRTS/User/";

    static int userFamilyPatientFLG=0,homeTabFlg=0,emergncyFlg=0,alertNotiStopFLG=0;
static String FirstName,MiddleName,LastName,Email,patientNameSend_server,User_State,User_City;
   static String[] TempState = {
            "Andhra Pradesh (AP)",
            "Arunachal Pradesh (AR)",
            "Assam (AS)",
            "Bihar (BR)",
            "Chhattisgarh (CG)",
            "Goa (GA)",
            "Gujarat (GJ)",
            "Haryana (HR)",
            "Himachal Pradesh (HP)",
            "Jammu and Kashmir (JK)",
            "Jharkhand (JH)",
            "Karnataka (KA)",
            "Kerala (KL)",
            "Madhya Pradesh (MP)",
                "q",
            "Manipur (MN)",
            "Meghalaya (ML)",
            "Mizoram (MZ)",
            "Nagaland (NL)",
            "Odisha(OR)",
            "Punjab (PB)",
            "Pondicherry (PY)",
            "Rajasthan (RJ)",
            "Sikkim (SK)",
            "Tamil Nadu (TN)",
            "Telangana (TS)",
            "Tripura (TR)",
            "Uttar Pradesh (UP)",
            "Uttarakhand (UK)",
            "West Bengal (WB)"
    };
  static   List<String> DoctorQualification,list,city_list, Andhra_Pradesh_list,Arunachal_Pradesh_list,Assam_list,Bihar_list,Chhattisgarh_list,Goa_list,Gujarat_list,  Haryana_list,Himachal_Pradesh_list,JammuAnd_Kashmir_list,Jharkhand_list,Karnataka_list,Madhya_Pradesh_list,Meghalaya_list;
    static   List<String>  Mizoram_list,Nagaland_list,Kerala_list,Maharashtra_list,Manipur_list,Odisha_list,Pondicherry_list,   Punjab_list,Rajasthan_list,Sikkim_list,Tamil_Nadu_list,Telangana_list,Tripura_list,Uttar_Pradesh_list,Uttarakhand_list,West_Bengal_list;
     State_and_City() {

         list = new ArrayList<String>();
         list.add("-- Select State --");
         list.add("Andhra Pradesh (AP)");
         list.add("Arunachal Pradesh (AR)");
         list.add("Assam (AS)");
         list.add("Bihar (BR)");
         list.add("Chhattisgarh (CG)");
         list.add("Goa (GA)");
         list.add("Gujarat (GJ)");
         list.add("Haryana (HR)");
         list.add("Himachal Pradesh (HP)");
         list.add("Jammu and Kashmir (JK)");
         list.add("Jharkhand (JH)");
         list.add("Karnataka (KA)");
         list.add("Kerala (KL)");
         list.add("Madhya Pradesh (MP)");
         list.add("Maharashtra (MH)");
         list.add("Manipur (MN)");
         list.add("Meghalaya (ML)");
         list.add("Mizoram (MZ)");
         list.add("Nagaland (NL)");
         list.add("Odisha(OR)");
         list.add("Punjab (PB)");
         list.add("Pondicherry (PY)");
         list.add("Rajasthan (RJ)");
         list.add("Sikkim (SK)");
         list.add("Tamil Nadu (TN)");
         list.add("Telangana (TS)");
         list.add("Tripura (TR)");
         list.add("Uttar Pradesh (UP)");
         list.add("Uttarakhand (UK)");
         list.add("West Bengal (WB)");


         city_list = new ArrayList<String>();
         city_list.add("-- Select City --");

         DoctorQualification = new ArrayList<String>();
         DoctorQualification.add("-- Select Qualification --");
         DoctorQualification.add("Audiologist");
         DoctorQualification.add("Allergist");
         DoctorQualification.add("Anesthesiologist");
         DoctorQualification.add("Cardiologist");
         DoctorQualification.add("Dentist");
         DoctorQualification.add("Dermatologist");
         DoctorQualification.add("Epidemiologist");
         DoctorQualification.add("Gynecologist");
         DoctorQualification.add("Immunologist");
         DoctorQualification.add("Infectious Disease Specialist");
         DoctorQualification.add("Medical Geneticist");
         DoctorQualification.add("Microbiologist");
         DoctorQualification.add("Neonatologist");
         DoctorQualification.add("Neurologist");
         DoctorQualification.add("Neurosurgeon");
         DoctorQualification.add("Obstetrician");
         DoctorQualification.add("Oncologist");
         DoctorQualification.add("Orthopedic Surgeon");
         DoctorQualification.add("ENT Specialist");
         DoctorQualification.add("Pediatrician");
         DoctorQualification.add("Physiologist");
         DoctorQualification.add("Plastic Surgeon");
         DoctorQualification.add("Podiatrist");
         DoctorQualification.add("Psychiatrist");
         DoctorQualification.add("Radiologist");
         DoctorQualification.add("Rheumatologist");
         DoctorQualification.add("Surgeon");
         DoctorQualification.add("Urologist");

         Andhra_Pradesh_list = new ArrayList<String>();
        Andhra_Pradesh_list.add("-- Select City --");
        Andhra_Pradesh_list.add("Adilabad");Andhra_Pradesh_list.add("Anantapur");Andhra_Pradesh_list.add("Chittoor");Andhra_Pradesh_list.add("Eluru");Andhra_Pradesh_list.add("Guntur");Andhra_Pradesh_list.add("Hyderabad");Andhra_Pradesh_list.add("Kadapa");Andhra_Pradesh_list.add("Kakinada");Andhra_Pradesh_list.add("Karimnagar");Andhra_Pradesh_list.add("Khammam");Andhra_Pradesh_list.add("Krishna");Andhra_Pradesh_list.add("Kurnool");Andhra_Pradesh_list.add("Mahbubnagar");Andhra_Pradesh_list.add("Medak");Andhra_Pradesh_list.add("Nalgonda");Andhra_Pradesh_list.add("Nellore");Andhra_Pradesh_list.add("Nizamabad");Andhra_Pradesh_list.add("Ongole");Andhra_Pradesh_list.add("Srikakulam");Andhra_Pradesh_list.add("Visakhapatnam");Andhra_Pradesh_list.add("Vizianagaram");Andhra_Pradesh_list.add("Warangal");

         Arunachal_Pradesh_list = new ArrayList<String>();
         Arunachal_Pradesh_list.add("-- Select City --");
         Arunachal_Pradesh_list.add("Anjaw");Arunachal_Pradesh_list.add("Changlang");Arunachal_Pradesh_list.add("Dibang Valley");Arunachal_Pradesh_list.add("East Siang");Arunachal_Pradesh_list.add("Kurung Kumey");Arunachal_Pradesh_list.add("Lohit");Arunachal_Pradesh_list.add("Lower Dibang Valley");Arunachal_Pradesh_list.add("Lower Subansiri");Arunachal_Pradesh_list.add("Papum Pare");Arunachal_Pradesh_list.add("Tawang");Arunachal_Pradesh_list.add("Tirap");Arunachal_Pradesh_list.add("Upper Siang");Arunachal_Pradesh_list.add("Upper Subansiri");Arunachal_Pradesh_list.add("West Kameng");Arunachal_Pradesh_list.add("West Siang");

         Assam_list = new ArrayList<String>();
         Assam_list.add("-- Select City --");
         Assam_list.add("Baksa");Assam_list.add("Barpeta");Assam_list.add("Bongaigaon");Assam_list.add("Cachar");Assam_list.add("Chirang");Assam_list.add("Darrang");Assam_list.add("Dhemaji");Assam_list.add("Dima Hasao");Assam_list.add("Dhubri");Assam_list.add("Dibrugarh");Assam_list.add("Goalpara");Assam_list.add("Golaghat");Assam_list.add("Hailakandi");Assam_list.add("Jorhat");Assam_list.add("Kamrup");Assam_list.add("Kamrup Metropolitan");Assam_list.add("Karbi Anglong");Assam_list.add("Karimganj");Assam_list.add("Kokrajhar");Assam_list.add("Lakhimpur");Assam_list.add("Marigaon");Assam_list.add("Nagaon");Assam_list.add("Nalbari");Assam_list.add("Sibsagar");Assam_list.add("Sonitpur");Assam_list.add("Tinsukia");Assam_list.add("Udalguri");

         Bihar_list = new ArrayList<String>();
         Bihar_list.add("-- Select City --");
         Bihar_list.add("Araria");Bihar_list.add("Arwal");Bihar_list.add("Aurangabad");Bihar_list.add("Banka");Bihar_list.add("Begusarai");Bihar_list.add("Bhagalpur");Bihar_list.add("Bhojpur");Bihar_list.add("Buxar");Bihar_list.add("Darbhanga");Bihar_list.add("East Champaran");Bihar_list.add("Gaya");Bihar_list.add("Gopalganj");Bihar_list.add("Jamui");Bihar_list.add("Jehanabad");Bihar_list.add("Kaimur");Bihar_list.add("Katihar");Bihar_list.add("Khagaria");Bihar_list.add("Kishanganj");Bihar_list.add("Lakhisarai");Bihar_list.add("Madhepura");Bihar_list.add("Madhubani");Bihar_list.add("Munger");Bihar_list.add("Muzaffarpur");Bihar_list.add("Nalanda");Bihar_list.add("Nawada");Bihar_list.add("Patna");Bihar_list.add("Purnia");Bihar_list.add("Rohtas");Bihar_list.add("Saharsa");Bihar_list.add("Samastipur");Bihar_list.add("Saran");Bihar_list.add("Sheikhpura");Bihar_list.add("Sheohar");Bihar_list.add("Sitamarhi");Bihar_list.add("Siwan");Bihar_list.add("Supaul");Bihar_list.add("Vaishali");Bihar_list.add("West Champaran");

         Chhattisgarh_list = new ArrayList<String>();
         Chhattisgarh_list.add("-- Select City --");
         Chhattisgarh_list.add("Bastar");Chhattisgarh_list.add("Bijapur");Chhattisgarh_list.add("Bilaspur");Chhattisgarh_list.add("Dantewada");Chhattisgarh_list.add("Dhamtari");Chhattisgarh_list.add("Durg");Chhattisgarh_list.add("Jashpur");Chhattisgarh_list.add("Janjgir-Champa");Chhattisgarh_list.add("Korba");Chhattisgarh_list.add("Koriya");Chhattisgarh_list.add("Kanker");Chhattisgarh_list.add("Kabirdham (Kawardha)");Chhattisgarh_list.add("Mahasamund");Chhattisgarh_list.add("Narayanpur");Chhattisgarh_list.add("Raigarh");Chhattisgarh_list.add("Rajnandgaon");Chhattisgarh_list.add("Raipur");Chhattisgarh_list.add("Surguja");

         Goa_list = new ArrayList<String>();
         Goa_list.add("-- Select City --");
         Goa_list.add("North Goa");Goa_list.add("South Goa");

         Gujarat_list = new ArrayList<String>();
         Gujarat_list.add("-- Select City --");
         Gujarat_list.add("Ahmedabad");Gujarat_list.add("Amreli district");Gujarat_list.add("Anand");Gujarat_list.add("Banaskantha");Gujarat_list.add("Bharuch");Gujarat_list.add("Bhavnagar");Gujarat_list.add("Dahod");Gujarat_list.add("The Dangs");Gujarat_list.add("Gandhinagar");Gujarat_list.add("Jamnagar");Gujarat_list.add("Junagadh");Gujarat_list.add("Kutch");Gujarat_list.add("Kheda");Gujarat_list.add("Mehsana");Gujarat_list.add("Narmada");Gujarat_list.add("Navsari");Gujarat_list.add("Patan");Gujarat_list.add("Panchmahal");Gujarat_list.add("Porbandar");Gujarat_list.add("Rajkot");Gujarat_list.add("Sabarkantha");Gujarat_list.add("Surendranagar");Gujarat_list.add("Surat");Gujarat_list.add("Vyara");Gujarat_list.add("Vadodara");Gujarat_list.add("Valsad");

         Haryana_list = new ArrayList<String>();
         Haryana_list.add("-- Select City --");
         Haryana_list.add("Ambala");Haryana_list.add("Bhiwani");Haryana_list.add("Faridabad");Haryana_list.add("Fatehabad");Haryana_list.add("Gurgaon");Haryana_list.add("Hissar");Haryana_list.add("Jhajjar");Haryana_list.add("Jind");Haryana_list.add("Karnal");Haryana_list.add("Kaithal");Haryana_list.add("Kurukshetra");Haryana_list.add("Mahendragarh");Haryana_list.add("Mewat");Haryana_list.add("Palwal");Haryana_list.add("Panchkula");Haryana_list.add("Panipat");Haryana_list.add("Rewari");Haryana_list.add("Rohtak");Haryana_list.add("Sirsa");Haryana_list.add("Sonipat");Haryana_list.add("Yamuna Nagar");

         Himachal_Pradesh_list = new ArrayList<String>();
         Himachal_Pradesh_list.add("-- Select City --");
         Himachal_Pradesh_list.add("Bilaspur");Himachal_Pradesh_list.add("Chamba");Himachal_Pradesh_list.add("Hamirpur");Himachal_Pradesh_list.add("Kangra");Himachal_Pradesh_list.add("Kinnaur");Himachal_Pradesh_list.add("Kullu");Himachal_Pradesh_list.add("Lahaul and Spiti");Himachal_Pradesh_list.add("Mandi");Himachal_Pradesh_list.add("Shimla");Himachal_Pradesh_list.add("Sirmaur");Himachal_Pradesh_list.add("Solan");Himachal_Pradesh_list.add("Una");

         JammuAnd_Kashmir_list = new ArrayList<String>();
         JammuAnd_Kashmir_list.add("-- Select City --");
         JammuAnd_Kashmir_list.add("Anantnag");JammuAnd_Kashmir_list.add("Badgam");JammuAnd_Kashmir_list.add("Bandipora");JammuAnd_Kashmir_list.add("Baramulla");JammuAnd_Kashmir_list.add("Doda");JammuAnd_Kashmir_list.add("Ganderbal");JammuAnd_Kashmir_list.add("Jammu");JammuAnd_Kashmir_list.add("Kargil");JammuAnd_Kashmir_list.add("Kathua");JammuAnd_Kashmir_list.add("Kishtwar");JammuAnd_Kashmir_list.add("Kupwara");JammuAnd_Kashmir_list.add("Kulgam");JammuAnd_Kashmir_list.add("Poonch");JammuAnd_Kashmir_list.add("Pulwama");JammuAnd_Kashmir_list.add("Rajauri");JammuAnd_Kashmir_list.add("Ramban");JammuAnd_Kashmir_list.add("Reasi");JammuAnd_Kashmir_list.add("Samba");JammuAnd_Kashmir_list.add("Shopian");JammuAnd_Kashmir_list.add("Srinagar");JammuAnd_Kashmir_list.add("Udhampur");

         Jharkhand_list = new ArrayList<String>();
         Jharkhand_list.add("-- Select City --");
         Jharkhand_list.add("Bokaro");Jharkhand_list.add("Chatra");Jharkhand_list.add("Deoghar");Jharkhand_list.add("Dhanbad");Jharkhand_list.add("Dumka");Jharkhand_list.add("East Singhbhum");Jharkhand_list.add("Garhwa");Jharkhand_list.add("Gumla");Jharkhand_list.add("Hazaribag");Jharkhand_list.add("Jamtara");Jharkhand_list.add("Khunti");Jharkhand_list.add("Koderma");Jharkhand_list.add("Latehar");Jharkhand_list.add("Lohardaga");Jharkhand_list.add("Pakur");Jharkhand_list.add("Ramgarh");Jharkhand_list.add("Ranchi");Jharkhand_list.add("Sahibganj");Jharkhand_list.add("Seraikela Kharsawan");Jharkhand_list.add("Simdega");Jharkhand_list.add("West Singhbhum");

         Karnataka_list = new ArrayList<String>();
         Karnataka_list.add("-- Select City --");
         Karnataka_list.add("Bagalkot");Karnataka_list.add("Bangalore Rural");Karnataka_list.add("Bangalore Urban");Karnataka_list.add("Belgaum");Karnataka_list.add("Bellary");Karnataka_list.add("Bidar");Karnataka_list.add("Bijapur");Karnataka_list.add("Chamarajnagar");Karnataka_list.add("Chikkamagaluru");Karnataka_list.add("Chikkaballapur");Karnataka_list.add("Chitradurga");Karnataka_list.add("Davanagere");Karnataka_list.add("Dharwad");Karnataka_list.add("Gulbarga");Karnataka_list.add("Hassan");Karnataka_list.add("Haveri district");Karnataka_list.add("Kodagu");Karnataka_list.add("Kolar");Karnataka_list.add("Koppal");Karnataka_list.add("Mandya");Karnataka_list.add("Mysore");Karnataka_list.add("Raichur");Karnataka_list.add("Shimoga");Karnataka_list.add("Tumkur");Karnataka_list.add("Udupi");Karnataka_list.add("Uttara Kannada");Karnataka_list.add("Ramanagara");Karnataka_list.add("Yadgir");

         Kerala_list = new ArrayList<String>();
         Kerala_list.add("-- Select City --");
         Kerala_list.add("Alappuzha");Kerala_list.add("Ernakulam");Kerala_list.add("Idukki");Kerala_list.add("Kannur");Kerala_list.add("Kasaragod");Kerala_list.add("Kollam");Kerala_list.add("Kottayam");Kerala_list.add("Kozhikode");Kerala_list.add("Malappuram");Kerala_list.add("Palakkad");Kerala_list.add("Pathanamthitta");Kerala_list.add("Thrissur");Kerala_list.add("Thiruvananthapuram");Kerala_list.add("Wayanad");


         Madhya_Pradesh_list = new ArrayList<String>();
         Madhya_Pradesh_list.add("-- Select City --");
         Madhya_Pradesh_list.add("Alirajpur");Madhya_Pradesh_list.add("Anuppur");Madhya_Pradesh_list.add("Ashok Nagar");Madhya_Pradesh_list.add("Balaghat");Madhya_Pradesh_list.add("Barwani");Madhya_Pradesh_list.add("Betul");Madhya_Pradesh_list.add("Bhind");Madhya_Pradesh_list.add("Bhopal");Madhya_Pradesh_list.add("Burhanpur");Madhya_Pradesh_list.add("Chhatarpur");Madhya_Pradesh_list.add("Chhindwara");Madhya_Pradesh_list.add("Damoh");Madhya_Pradesh_list.add("Datia");Madhya_Pradesh_list.add("Dewas");Madhya_Pradesh_list.add("Dindori");Madhya_Pradesh_list.add("Guna");Madhya_Pradesh_list.add("Gwalior");Madhya_Pradesh_list.add("Harda");Madhya_Pradesh_list.add("Hoshangabad");Madhya_Pradesh_list.add("Indore");Madhya_Pradesh_list.add("Jabalpur");Madhya_Pradesh_list.add("Jhabua");Madhya_Pradesh_list.add("Katni");Madhya_Pradesh_list.add("Khandwa (East Nimar)");Madhya_Pradesh_list.add("Khargone (West Nimar)");Madhya_Pradesh_list.add("Mandla");Madhya_Pradesh_list.add("Mandsaur");Madhya_Pradesh_list.add("Narsinghpur");Madhya_Pradesh_list.add("Neemuch");Madhya_Pradesh_list.add("Panna");Madhya_Pradesh_list.add("Rewa");Madhya_Pradesh_list.add("Rajgarh");Madhya_Pradesh_list.add("Ratlam");Madhya_Pradesh_list.add("Raisen");Madhya_Pradesh_list.add("Sagar");Madhya_Pradesh_list.add("Satna");Madhya_Pradesh_list.add("Sehore");Madhya_Pradesh_list.add("Seoni");Madhya_Pradesh_list.add("Shahdol");Madhya_Pradesh_list.add("Shajapur");Madhya_Pradesh_list.add("Sheopur");Madhya_Pradesh_list.add("Shivpuri");Madhya_Pradesh_list.add("Sidhi");Madhya_Pradesh_list.add("Singrauli");Madhya_Pradesh_list.add("Tikamgarh");Madhya_Pradesh_list.add("Ujjain");Madhya_Pradesh_list.add("Umaria");Madhya_Pradesh_list.add("Vidisha");

         Meghalaya_list = new ArrayList<String>();
         Meghalaya_list.add("-- Select City --");

         Meghalaya_list.add("East Garo Hills");Meghalaya_list.add("East Khasi Hills");Meghalaya_list.add("Jaintia Hills");Meghalaya_list.add("Ri Bhoi");Meghalaya_list.add("South Garo Hills");Meghalaya_list.add("West Garo Hills");Meghalaya_list.add("West Khasi Hills");

         Mizoram_list = new ArrayList<String>();
         Mizoram_list.add("-- Select City --");
         Mizoram_list.add("Aizawl");Mizoram_list.add("Champhai");Mizoram_list.add("Kolasib");Mizoram_list.add("Lunglei");Mizoram_list.add("Mamit");Mizoram_list.add("Saiha");Mizoram_list.add("Serchhip");

         Nagaland_list = new ArrayList<String>();
         Nagaland_list.add("-- Select City --");
         Nagaland_list.add("Dimapur");Nagaland_list.add("Kohima");Nagaland_list.add("Mokokchung");Nagaland_list.add("Mon");Nagaland_list.add("Phek");Nagaland_list.add("Tuensang");Nagaland_list.add("Wokha");Nagaland_list.add("Zunheboto");

         Maharashtra_list = new ArrayList<String>();
         Maharashtra_list.add("-- Select City --");
         Maharashtra_list.add("Ahmednagar");Maharashtra_list.add("Amravati");Maharashtra_list.add("Aurangabad");Maharashtra_list.add("Bhandara");Maharashtra_list.add("Buldhana");Maharashtra_list.add("Chandrapur");Maharashtra_list.add("Dhule");Maharashtra_list.add("Gadchiroli");Maharashtra_list.add("Gondia");Maharashtra_list.add("Hingoli");Maharashtra_list.add("Jalgaon");Maharashtra_list.add("Jalna");Maharashtra_list.add("Kolhapur");Maharashtra_list.add("Latur");Maharashtra_list.add("Mumbai City");Maharashtra_list.add("Mumbai suburban");Maharashtra_list.add("Nandurbar");Maharashtra_list.add("Nanded");Maharashtra_list.add("Nagpur");Maharashtra_list.add("Nashik");Maharashtra_list.add("Osmanabad");Maharashtra_list.add("Parbhani");Maharashtra_list.add("Pune");Maharashtra_list.add("Raigad");Maharashtra_list.add("Ratnagiri");Maharashtra_list.add("Sindhudurg");Maharashtra_list.add("Sangli");Maharashtra_list.add("Solapur");Maharashtra_list.add("Satara");Maharashtra_list.add("Thane");Maharashtra_list.add("Wardha");Maharashtra_list.add("Washim");Maharashtra_list.add("Yavatmal");

         Manipur_list = new ArrayList<String>();
         Manipur_list.add("-- Select City --");
         Manipur_list.add("Bishnupur");Manipur_list.add("Churachandpur");Manipur_list.add("Chandel");Manipur_list.add("Imphal East");Manipur_list.add("Senapati");Manipur_list.add("Tamenglong");Manipur_list.add("Thoubal");Manipur_list.add("Ukhrul");Manipur_list.add("Imphal West");

         Odisha_list = new ArrayList<String>();
         Odisha_list.add("-- Select City --");
         Odisha_list.add("Angul");Odisha_list.add("Boudh (Bauda)");Odisha_list.add("Bhadrak");Odisha_list.add("Balangir");Odisha_list.add("Bargarh (Baragarh)");Odisha_list.add("Balasore");Odisha_list.add("Cuttack");Odisha_list.add("Debagarh (Deogarh)");Odisha_list.add("Dhenkanal");Odisha_list.add("Ganjam");Odisha_list.add("Gajapati");Odisha_list.add("Jharsuguda");Odisha_list.add("Jajpur");Odisha_list.add("Jagatsinghpur");Odisha_list.add("Kendujhar (Keonjhar)");Odisha_list.add("Kalahandi");Odisha_list.add("Kandhamal");Odisha_list.add("Koraput");Odisha_list.add("Kendrapara");Odisha_list.add("Malkangiri");Odisha_list.add("Mayurbhanj");Odisha_list.add("Nabarangpur");Odisha_list.add("Nuapada");Odisha_list.add("Nayagarh");Odisha_list.add("Puri");Odisha_list.add("Rayagada");Odisha_list.add("Sambalpur");Odisha_list.add("Subarnapur (Sonepur)");Odisha_list.add("Sundergarh");

         Pondicherry_list = new ArrayList<String>();
         Pondicherry_list.add("-- Select City --");

         Pondicherry_list.add("Karaikal");Pondicherry_list.add("Mahe");Pondicherry_list.add("Pondicherry");Pondicherry_list.add("Yanam");

          Punjab_list = new ArrayList<String>();
         Punjab_list.add("-- Select City --");
         Punjab_list.add("Amritsar");Punjab_list.add("Barnala");Punjab_list.add("Bathinda");Punjab_list.add("Firozpur");Punjab_list.add("Faridkot");Punjab_list.add("Fatehgarh Sahib");Punjab_list.add("Fazilka");Punjab_list.add("Gurdaspur");Punjab_list.add("Hoshiarpur");Punjab_list.add("Jalandhar");Punjab_list.add("Kapurthala");Punjab_list.add("Ludhiana");Punjab_list.add("Mansa");Punjab_list.add("Moga");Punjab_list.add("Sri Muktsar Sahib");Punjab_list.add("Pathankot");Punjab_list.add("Patiala");Punjab_list.add("Rupnagar");Punjab_list.add("Ajitgarh (Mohali)");Punjab_list.add("Sangrur");Punjab_list.add("Nawanshahr");Punjab_list.add("Tarn Taran");

          Rajasthan_list = new ArrayList<String>();
         Rajasthan_list.add("-- Select City --");
         Rajasthan_list.add("Ajmer");Rajasthan_list.add("Alwar");Rajasthan_list.add("Bikaner");Rajasthan_list.add("Barmer");Rajasthan_list.add("Banswara");Rajasthan_list.add("Bharatpur");Rajasthan_list.add("Baran");Rajasthan_list.add("Bundi");Rajasthan_list.add("Bhilwara");Rajasthan_list.add("Churu");Rajasthan_list.add("Chittorgarh");Rajasthan_list.add("Dausa");Rajasthan_list.add("Dholpur");Rajasthan_list.add("Dungapur");Rajasthan_list.add("Ganganagar");Rajasthan_list.add("Hanumangarh");Rajasthan_list.add("Jhunjhunu");Rajasthan_list.add("Jalore");Rajasthan_list.add("Jodhpur");Rajasthan_list.add("Jaipur");Rajasthan_list.add("Jaisalmer");Rajasthan_list.add("Jhalawar");Rajasthan_list.add("Karauli");Rajasthan_list.add("Kota");Rajasthan_list.add("Nagaur");Rajasthan_list.add("Pali");Rajasthan_list.add("Pratapgarh");Rajasthan_list.add("Rajsamand");Rajasthan_list.add("Sikar");Rajasthan_list.add("Sawai Madhopur");Rajasthan_list.add("Sirohi");Rajasthan_list.add("Tonk");Rajasthan_list.add("Udaipur");

          Sikkim_list = new ArrayList<String>();
         Sikkim_list.add("-- Select City --");
         Sikkim_list.add("East Sikkim");Sikkim_list.add("North Sikkim");Sikkim_list.add("South Sikkim");Sikkim_list.add("West Sikkim");

          Tamil_Nadu_list = new ArrayList<String>();
         Tamil_Nadu_list.add("-- Select City --");
         Tamil_Nadu_list.add("Ariyalur");Tamil_Nadu_list.add("Chennai");Tamil_Nadu_list.add("Coimbatore");Tamil_Nadu_list.add("Cuddalore");Tamil_Nadu_list.add("Dharmapuri");Tamil_Nadu_list.add("Dindigul");Tamil_Nadu_list.add("Erode");Tamil_Nadu_list.add("Kanchipuram");Tamil_Nadu_list.add("Kanyakumari");Tamil_Nadu_list.add("Karur");Tamil_Nadu_list.add("Madurai");Tamil_Nadu_list.add("Nagapattinam");Tamil_Nadu_list.add("Nilgiris");Tamil_Nadu_list.add("Namakkal");Tamil_Nadu_list.add("Perambalur");Tamil_Nadu_list.add("Pudukkottai");Tamil_Nadu_list.add("Ramanathapuram");Tamil_Nadu_list.add("Salem");Tamil_Nadu_list.add("Sivaganga");Tamil_Nadu_list.add("Tirupur");Tamil_Nadu_list.add("Tiruchirappalli");Tamil_Nadu_list.add("Theni");Tamil_Nadu_list.add("Tirunelveli");Tamil_Nadu_list.add("Thanjavur");Tamil_Nadu_list.add("Thoothukudi");Tamil_Nadu_list.add("Tiruvallur");Tamil_Nadu_list.add("Tiruvarur");Tamil_Nadu_list.add("Tiruvannamalai");Tamil_Nadu_list.add("Vellore");Tamil_Nadu_list.add("Viluppuram");Tamil_Nadu_list.add("Virudhunagar");

          Telangana_list = new ArrayList<String>();
         Telangana_list.add("-- Select City --");Telangana_list.add("Hyderabad ");
         Telangana_list.add("Warangal");Telangana_list.add("Nalgonda");Telangana_list.add("Mahabubnagar");Telangana_list.add("Khammam");Telangana_list.add("Ramagundam");Telangana_list.add("Nizamabad");Telangana_list.add("Miryalaguda");Telangana_list.add("Suryapet");Telangana_list.add("Karimnagar");Telangana_list.add("Adilabad");

          Tripura_list = new ArrayList<String>();
         Tripura_list.add("-- Select City --");
         Tripura_list.add("Dhalai");Tripura_list.add("North Tripura");Tripura_list.add("South Tripura");Tripura_list.add("Khowai");Tripura_list.add("West Tripura");

          Uttar_Pradesh_list = new ArrayList<String>();
         Uttar_Pradesh_list.add("-- Select City --");
         Uttar_Pradesh_list.add("Agra");Uttar_Pradesh_list.add("Allahabad");Uttar_Pradesh_list.add("Aligarh");Uttar_Pradesh_list.add("Ambedkar Nagar");Uttar_Pradesh_list.add("Auraiya");Uttar_Pradesh_list.add("Azamgarh");Uttar_Pradesh_list.add("Barabanki");Uttar_Pradesh_list.add("Budaun");Uttar_Pradesh_list.add("Bagpat");Uttar_Pradesh_list.add("Bahraich");Uttar_Pradesh_list.add("Bijnor");Uttar_Pradesh_list.add("Ballia");Uttar_Pradesh_list.add("Banda");Uttar_Pradesh_list.add("Balrampur");Uttar_Pradesh_list.add("Bareilly");Uttar_Pradesh_list.add("Basti");Uttar_Pradesh_list.add("Bulandshahr");Uttar_Pradesh_list.add("Chandauli");Uttar_Pradesh_list.add("Chhatrapati Shahuji");Uttar_Pradesh_list.add("Chitrakoot");Uttar_Pradesh_list.add("Deoria");Uttar_Pradesh_list.add("Etah");Uttar_Pradesh_list.add("Kanshi Ram Nagar");Uttar_Pradesh_list.add("Etawah");Uttar_Pradesh_list.add("Firozabad");Uttar_Pradesh_list.add("Farrukhabad");Uttar_Pradesh_list.add("Fatehpur");Uttar_Pradesh_list.add("Faizabad");Uttar_Pradesh_list.add("Gautam Buddh Nagar");Uttar_Pradesh_list.add("Gonda");Uttar_Pradesh_list.add("Ghazipur");Uttar_Pradesh_list.add("Gorakhpur");Uttar_Pradesh_list.add("Ghaziabad");Uttar_Pradesh_list.add("Hamirpur");Uttar_Pradesh_list.add("Hardoi");Uttar_Pradesh_list.add("Mahamaya Nagar");Uttar_Pradesh_list.add("Jhansi");Uttar_Pradesh_list.add("Jalaun");Uttar_Pradesh_list.add("Jyotiba Phule Nagar");Uttar_Pradesh_list.add("Jaunpur district");Uttar_Pradesh_list.add("Ramabai Nagar");Uttar_Pradesh_list.add("Kannauj");Uttar_Pradesh_list.add("Kanpur");Uttar_Pradesh_list.add("Kaushambi");Uttar_Pradesh_list.add("Kushinagar");Uttar_Pradesh_list.add("Lalitpur");Uttar_Pradesh_list.add("Lakhimpur Kheri");
         Uttar_Pradesh_list.add("Lucknow");Uttar_Pradesh_list.add("Mau");Uttar_Pradesh_list.add("Meerut");Uttar_Pradesh_list.add("Maharajganj");Uttar_Pradesh_list.add("Mahoba");Uttar_Pradesh_list.add("Mirzapur");Uttar_Pradesh_list.add("Moradabad");Uttar_Pradesh_list.add("Mainpuri");Uttar_Pradesh_list.add("Mathura");Uttar_Pradesh_list.add("Muzaffarnagar");Uttar_Pradesh_list.add("Panchsheel Nagar dist.");Uttar_Pradesh_list.add("Pilibhit");Uttar_Pradesh_list.add("Shamli");Uttar_Pradesh_list.add("Pratapgarh");Uttar_Pradesh_list.add("Rampur");Uttar_Pradesh_list.add("Raebareli");Uttar_Pradesh_list.add("Saharanpur");Uttar_Pradesh_list.add("Sitapur");Uttar_Pradesh_list.add("Shahjahanpur");Uttar_Pradesh_list.add("Sant Kabir Nagar");Uttar_Pradesh_list.add("Siddharthnagar");Uttar_Pradesh_list.add("Sonbhadra");Uttar_Pradesh_list.add("Sant Ravidas Nagar");Uttar_Pradesh_list.add("Sultanpur");Uttar_Pradesh_list.add("Shravasti");Uttar_Pradesh_list.add("Unnao");Uttar_Pradesh_list.add("Varanasi");

          Uttarakhand_list = new ArrayList<String>();
         Uttarakhand_list.add("-- Select City --");
         Uttarakhand_list.add("Almora");Uttarakhand_list.add("Bageshwar");Uttarakhand_list.add("Chamoli");Uttarakhand_list.add("Champawat");Uttarakhand_list.add("Dehradun");Uttarakhand_list.add("Haridwar");Uttarakhand_list.add("Nainital");Uttarakhand_list.add("Pauri Garhwal");Uttarakhand_list.add("Pithoragarh");Uttarakhand_list.add("Rudraprayag");Uttarakhand_list.add("Tehri Garhwal");Uttarakhand_list.add("Udham Singh Nagar");Uttarakhand_list.add("Uttarkashi");

         West_Bengal_list = new ArrayList<String>();
         West_Bengal_list.add("-- Select City --");
         West_Bengal_list.add("Birbhum");West_Bengal_list.add("Bankura");West_Bengal_list.add("Bardhaman");West_Bengal_list.add("Dakshin Dinajpur");West_Bengal_list.add("Hooghly");West_Bengal_list.add("Howrah");West_Bengal_list.add("Jalpaiguri");West_Bengal_list.add("Cooch Behar");West_Bengal_list.add("Kolkata");West_Bengal_list.add("Maldah");West_Bengal_list.add("Paschim Medinipur");West_Bengal_list.add("Purba Medinipur");West_Bengal_list.add("Murshidabad");West_Bengal_list.add("Nadia");

     }

    // add items into spinner dynamically
    public void addItemsOnSpinner2(String str, Spinner cit, Context applicationContext) {
Spinner city=cit;
        //Toast.makeText(this, "str"+str.replace(' ','_')+"_list", Toast.LENGTH_LONG).show();
        String temp = "State_and_City." + str.replace(' ', '_') + "_list";

        if (str.equals("Andhra Pradesh (AP)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Andhra_Pradesh_list);
            city.setAdapter(dataAdapter);

        } else if (str.equals("Assam (AS)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Assam_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Bihar (BR)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Bihar_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Chhattisgarh (CG)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Chhattisgarh_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Goa (GA)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Goa_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Gujarat (GJ)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Gujarat_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Haryana (HR)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Haryana_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Himachal Pradesh (HP)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Himachal_Pradesh_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Jammu and Kashmir (JK)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.JammuAnd_Kashmir_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Jharkhand (JH)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Jharkhand_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Karnataka (KA)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Karnataka_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Kerala (KL)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Kerala_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Madhya Pradesh (MP)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Madhya_Pradesh_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Maharashtra (MH)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Maharashtra_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Manipur (MN)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Manipur_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Meghalaya (ML)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Meghalaya_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Mizoram (MZ)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Mizoram_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Nagaland (NL)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Nagaland_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Odisha(OR)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Odisha_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Punjab (PB)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Punjab_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Pondicherry (PY)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Pondicherry_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Rajasthan (RJ)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Rajasthan_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Sikkim (SK)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Sikkim_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Tamil Nadu (TN)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Tamil_Nadu_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Telangana (TS)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Telangana_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Tripura (TR)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Tripura_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Uttar Pradesh (UP)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Uttar_Pradesh_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Uttarakhand (UK)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Uttarakhand_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Andhra Pradesh (AP)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Andhra_Pradesh_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("West Bengal (WB)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.West_Bengal_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("-- Select State --")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.city_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Arunachal Pradesh (AR)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, State_and_City.Arunachal_Pradesh_list);
            city.setAdapter(dataAdapter);



        }
    }

}
