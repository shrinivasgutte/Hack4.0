<?php

/*$structure = '../Hello';

if (!mkdir($structure, 0777, true)) {
    die('Failed to create folders...');
}*/
/*
include '../db.php';

$LastName=$FirstName=$MiddleName=$Address=$ContactNo=$Gender=$DOB=$BloodGrp=$UserName=$Password=$NfcID=$Email=$AadharNo=$State=$City="";
$Type="Patient";

//$sql="INSERT INTO `users`(`LastName`, `FirstName`, `MiddleName`,  `Address`, `ContactNo`, `Type`, `Gender`, `DOB`, `BloodGrp`, `UserName`, `Password`, `NfcID`,  `Email`,  `AadharNo`, `State`, `City`) VALUES ('$LastName','$FirstName','$MiddleName','$Address','$ContactNo','$Type','$Gender','$DOB','$BloodGrp','$UserName','$Password','$NfcID','$Email','$AadharNo','$State','$City')";
$sql="";
if ($conn->query($sql) === TRUE) {
    $last_id = $conn->insert_id;
    echo "New record created successfully. Last inserted ID is: " . $last_id;
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;

}*/


echo "Created date is " . date("Ymdhis");