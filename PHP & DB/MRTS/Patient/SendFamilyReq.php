 <?php
   
include '../db.php';
	$pID=$_POST['PatientID'];
   $sID=$_POST['SenderID'];
    $rel=$_POST['Relation'];

	
//	$n="S";
 //   $m="HG";
//	$e="R";
//	$a="D";
//	$dob="w";
  
//	$b="s";
//	$delete="NO";
//	$nfc_id="9988";
	
	
$sql="INSERT INTO `familymember` VALUES ($pID,'$rel',$sID,'Pending','Normal','Normal',0,0,0)";
$conn->query($sql);
/*if($result->num_rows==0)
{
	echo "fail ";
}
else
{
	echo "pass ";
}*/

	/*
	if($delete == 'yes')
	{
		$sql="DELETE FROM doctorbasicdata WHERE `ID`='$nfc_id'";
		 $conn->query($sql);
		echo "delete";
		
	}else{
		$sql="UPDATE `doctorbasicdata` SET `DoctorName`='$n',`MobileNo`='$m',`Email`='$e',`DoctorAddress`='$a',`BloodGroup`='$b',`DateOfBirth`='$dob' WHERE `ID`='$nfc_id'";
		
		
        $conn->query($sql);
        echo "RECORDS updated SUCCESSFULLY";
	}*/
 ?>