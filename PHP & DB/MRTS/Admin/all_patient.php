<?php

include '../db.php';
 
  // $url=basename($_SERVER['REQUEST_URI']);
//  $parts=parse_url($url);
 	//parse_str($parts['query'], $query);
	
//	$p_id=$query['p_id']; // Fetching URL Parameter
	


	$sql = "SELECT
users.LastName,
users.FirstName,
users.MiddleName,
users.Address,
users.ContactNo,
users.Gender,
users.Email,
users.ID

FROM
users
WHERE
users.Type = 'Patient'";//he all dr chayy
	//$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	
	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array(
'D_Last_name'=>$row[0],
'D_first_name'=>$row[1],
'D_mid_name'=>$row[2],
'dr_Address'=>$row[3],
'D_ContactNo'=>$row[4],
'D_Gender'=>$row[5],
'D_Email'=>$row[6],
'p_id'=>$row[7]

));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>