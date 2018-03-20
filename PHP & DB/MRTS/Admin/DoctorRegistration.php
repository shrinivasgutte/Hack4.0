<?php

include '../db.php';

//init
$Allergies=$LastName=$FirstName=$MiddleName=$Address=$ContactNo=$Gender=$DOB=$BloodGrp=$UserName=$Password=$NfcID=$Email=$AadharNo=$State=$City="";
$Type="Doctor";
$structure = '../User/';
//END init

//GETDATA

if(isset($_POST['LastName'])) {
    $LastName = $_POST['LastName'];
    $FirstName = $_POST['FirstName'];
    $MiddleName = $_POST['MiddleName'];
    $Address = $_POST['Address'];
    $ContactNo = $_POST['ContactNo'];
    $Gender = $_POST['Gender'];
    $DOB = $_POST['DOB'];
    $BloodGrp = $_POST['BloodGrp'];
    $UserName = $_POST['UserName'];
    $Password = $_POST['Password'];
    $NfcID = $_POST['NfcID'];
    $Email = $_POST['Email'];
    $AadharNo = $_POST['AadharNo'];
    $State = $_POST['State'];
    $City = $_POST['City'];
    $Allergies=$_POST['Allergies'];
	
    $String_Qualification = $_POST['String_Qualification'];
	$String_Speciality = $_POST['String_Speciality'];
	$String_CertificateNo = $_POST['String_CertificateNo'];
	$Dr_Hosp_ID = $_POST['Dr_Hosp_ID'];
}
else
{
    die("No Data");
}

//End GETDATA

//TESTDATA
/*
$LastName="Rao";
$FirstName="Aman";
$MiddleName="Amol";
$Address="Warje Pune";
$ContactNo="9011689890";
$Gender="Male";
$DOB="30/09/1997";
$BloodGrp="B+";
$UserName="amanrao1997";
$Password="123456";
$NfcID="011110";
$Email="amanrao1997@gmail.com";
$AadharNo="123456789014";
$State="Maharastra";
$City="Pune";
$Allergies="Dust";
*/
//End TESTDATA


$sql="INSERT INTO `users`(`LastName`, `FirstName`, `MiddleName`,  `Address`, `ContactNo`, `Type`, `Gender`, `DOB`, `BloodGrp`, `UserName`, `Password`, `NfcID`,  `Email`,  `AadharNo`, `State`, `City`) VALUES ('$LastName','$FirstName','$MiddleName','$Address','$ContactNo','$Type','$Gender','$DOB','$BloodGrp','$UserName','$Password','$NfcID','$Email','$AadharNo','$State','$City')";
if ($conn->query($sql) === TRUE) {
    $last_id = $conn->insert_id;
    $sql="INSERT INTO `patient`(`ID`, `Allergies`) VALUES ('$last_id','$Allergies')";
    $conn->query($sql);
	
	 $sql="INSERT INTO `doctor`(`DoctorID`, `Qualification`, `Speciality`, `CertificateNo`, `HospitalID`) VALUES ('$last_id','$String_Qualification','$String_Speciality','$String_CertificateNo','$Dr_Hosp_ID')";
    $conn->query($sql);
	
    $structure=$structure.$last_id;
    if (!mkdir($structure, 0777, true)) {
        die('Failed to create folders...');
    }
    elseif (!mkdir($structure.'/Casepaper', 0777, true)) {
        die('Failed to create folders...');
    }
    elseif (!mkdir($structure.'/Reports', 0777, true)) {
        die('Failed to create folders...');
    }
    elseif (!mkdir($structure.'/Prescription', 0777, true)) {
        die('Failed to create folders...');
    }     echo "Success : ".$last_id;
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
