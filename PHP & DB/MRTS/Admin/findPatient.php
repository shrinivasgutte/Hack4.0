<?php

include '../db.php';
 
   $url=basename($_SERVER['REQUEST_URI']);
$parts=parse_url($url);
 	parse_str($parts['query'], $query);
	
	$State=$query['State']; // Fetching URL Parameter
	$City=$query['City']; // Fetching URL Parameter
	
	//$State="Maharashtra";
	//$City="Pune";
 

	$sql = "SELECT
	users.ID,
	users.LastName,
	users.FirstName,
	users.MiddleName,
	users.Address,
	users.ContactNo,
	users.Gender,
	users.Email
	FROM
	users
	WHERE
	users.State='$State'  and  users.City='$City' and users.Type='Patient'";
	
	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('DoctorID'=>$row[0],

'D_Last_name'=>$row[1],
'D_first_name'=>$row[2],
'D_mid_name'=>$row[3],
'dr_Address'=>$row[4],
'D_ContactNo'=>$row[5],
'D_Gender'=>$row[6],
'D_Email'=>$row[7]
));
}
 //'HospitalID'=>$row[3],
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
?>