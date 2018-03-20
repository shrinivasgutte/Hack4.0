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
medical.MedicalID,
medical.`Name`,
medical.Address,
medical.ContactNo,
medical.WorkerID
FROM
medical
 where State='$State'  and  City='$City'";
	//$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	
	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('MedicalID'=>$row[0],
'Name'=>$row[1],
'Address'=>$row[2],
'ContactNo'=>$row[3],
'WorkerID'=>$row[4]
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>