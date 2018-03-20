<?php

include '../db.php';

if(isset($_POST['image']))
{
    $Description=$_POST['Description'];
    $Type=$_POST['Type'];
	 $Name=$_POST['name'];
    $DoctorID=$_POST['DoctorID'];
    $PatientID=$_POST['PatientID'];
    $LabID=$_POST['LabID'];
    $image=$_POST['image'];
   
	$Time=$_POST['date_time'];
}
else
{
  // die("No Data");
  /* $Description="a";
    $Type="b";
    $DoctorID="66778899";
    $PatientID="11223344";
    $LabID="12";
    $Date="4/4/2012";
    $image="c";
    $Name="ss";
	$Time="23";*/
}

$Pic=date("Ymdhis")."jpg";
/*
$oldimg='../Temp/'.$image."jpg";
$newimg='../'.$PatientID.'/Reports/'.$Pic;
    rename($oldimg, $newimg);
*/
$sql="INSERT INTO `reports`  VALUES ('$DoctorID','$PatientID',' $LabID','$Type','$Name','$Description','$Pic','$Time')";
if ($conn->query($sql) === TRUE)
    echo "Success";
else
    echo "Failed";