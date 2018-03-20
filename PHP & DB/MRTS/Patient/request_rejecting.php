<?php

include '../db.php';
 
	$r=$_POST['rej'];
	$p_id=$_POST['p_id'];
  //$r=2;
	/*9$id=12;
    $name="hello";
	$address="abce";
	$mobile=1234567890;
    $quali="MBS";
    $email="abcde@abcd.com";
    $uid="123456789012";
    $reg="hudmiij";
	$password="0123456789";*/
	
	
	$sql = "UPDATE `familymember` SET `Accepted`= 'Rejected' WHERE `PrimaryID` ='$p_id'  and `MemberID` ='$r'";
        $conn->query($sql);
        echo "Data Rejected";
      
	

	
	$res = mysqli_query($conn,$sql);
 
 

 
mysqli_close($conn);
 
?>

