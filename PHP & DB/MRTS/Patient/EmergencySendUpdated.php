 <?php
   
include '../db.php';
	$pID=$_POST['PatientID'];
   $sID=$_POST['SenderID'];
   $loc=$_POST['Location']; 
//$pID="77438199";
 //  $sID="11223344";
	
	
//	$n="S";
 //   $m="HG";
//	$e="R";
//	$a="D";
//	$dob="w";
  
//	$b="s";
//	$delete="NO";
//	$nfc_id="9988";
	
	
$sql="UPDATE `familymember` SET  `Secondary_EmergencyAction`='Emergency',`Location`='$loc',`OtherSenderID`='$sID' WHERE `MemberID`='$pID'  and `Accepted`='Accepted'";
$conn->query($sql);

 ?>