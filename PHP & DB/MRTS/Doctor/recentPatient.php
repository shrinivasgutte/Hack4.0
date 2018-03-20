<?php

include '../db.php';
 
   $url=basename($_SERVER['REQUEST_URI']);
$parts=parse_url($url);
 	parse_str($parts['query'], $query);
	
	$p_id=$query['p_id']; // Fetching URL Parameter
	
	
 
	$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	 /*  

	,hospital.Name,hospital.Address,hospital.ContactNo                                                                                 */

	$sql = "SELECT
users.ID,
users.LastName,
users.FirstName,
users.MiddleName,
users.ContactNo,
users.Email
FROM
casepaper
INNER JOIN users ON casepaper.PatientID = users.ID WHERE casepaper.DoctorID=12345678 
HAVING
count(*) > 0";
	//$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	
	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('ID'=>$row[0],
'LastName'=>$row[1],
'FirstName'=>$row[2],
'MiddleName'=>$row[3],
'ContactNo'=>$row[4],
'Email'=>$row[5]
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>