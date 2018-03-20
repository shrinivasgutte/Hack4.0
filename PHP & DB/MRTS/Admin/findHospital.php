<?php

include '../db.php';
 
   $url=basename($_SERVER['REQUEST_URI']);
$parts=parse_url($url);
 	parse_str($parts['query'], $query);
	
	$State=$query['State']; // Fetching URL Parameter
	$City=$query['City']; // Fetching URL Parameter
	
	
  /*  

	,hospital.Name,hospital.Address,hospital.ContactNo                                                                                 */

	$sql = "SELECT
hospital.HospitalID,
hospital.`Name`,
hospital.Address,
hospital.ContactNo,
hospital.EmergencyNo,
hospital.WorkingHours
FROM
hospital
 where State='$State'  and  City='$City'";
	//$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	
	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('HospitalID'=>$row[0],
'Name'=>$row[1],
'Address'=>$row[2],
'ContactNo'=>$row[3],
'EmergencyNo'=>$row[4],
'WorkingHours'=>$row[5]
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>