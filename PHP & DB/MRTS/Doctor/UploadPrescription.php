<?php

include '../db.php';

/*
$url=basename($_SERVER['REQUEST_URI']);
$parts=parse_url($url);
 	parse_str($parts['query'], $query);
	
	$PatientID=$query['PatientID']; // Fetching URL Parameter
$DoctorID=$query['DoctorID']; // Fetching URL Parameter
$Priscriptionid=$query['Priscri']; // Fetching URL Parameter
$Time=$query['date_time']; // Fetching URL Parameter

   */
if(isset($_POST['date_time']))
{
    $PatientID=$_POST['PatientID'];
    $DoctorID=$_POST['DoctorID'];
	
    //$Pris=$_POST['Pris'];


	$Time=$_POST['date_time'];
}
else
{
	 echo "Failed";
}
    
	


/*
$oldimg='../Temp/'.$image."jpg";
$newimg='../'.$PatientID.'/Prescription/'.$Pic;
    rename($oldimg, $newimg);*/
$sql="INSERT INTO `prescription` (`PatientID`, `DoctorID`, `Pic`, `Date`, `MdedicalID`) VALUES ('$PatientID','$DoctorID','Vincent.jpg','$Time','75315985')";
if ($conn->query($sql) === TRUE)
    echo "Success";
else
    echo "Failed22";
?>