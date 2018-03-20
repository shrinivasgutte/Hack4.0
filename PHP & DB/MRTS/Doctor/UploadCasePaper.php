<?php

include '../db.php';

if(isset($_POST['image']))
{
   $Description=$_POST['Description'];
    $Type=$_POST['Type'];
    $DoctorID=$_POST['DoctorID'];
    $PatientID=$_POST['PatientID'];
    $HospitalID=$_POST['HospitalID'];
    $image=$_POST['image'];
	$Time=$_POST['date_time'];
	
	/* $Description=1;
    $Type=2;
    $DoctorID=3;
    $PatientID=4;
    $HospitalID=5;
    $image=7;*/
	
}
else
{
    die("No Data");
   /* $Description=1;
    $Type=2;
    $DoctorID=3;
    $PatientID=4;
    $HospitalID=5;
    $image=7;*/
		
}

//$Pic=date("Vincent")."jpg";
$Pic="Vincent.jpg";
//echo "$Pic";

//$oldimg='../Temp/'.$image."jpg";
//$newimg='../User/'.$PatientID.'/Casepaper/'.$Pic;
   // rename($oldimg, $newimg);
$sql="INSERT INTO `casepaper`  VALUES ('$Description','$Type','$DoctorID','$PatientID','$HospitalID','$Pic','$Time')";
if ($conn->query($sql) === TRUE)
    echo "Success";
else
    echo "Failed";