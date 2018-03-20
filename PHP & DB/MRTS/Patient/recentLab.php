<?php

include '../db.php';
 
   $url=basename($_SERVER['REQUEST_URI']);
$parts=parse_url($url);
 	parse_str($parts['query'], $query);
	
	$p_id=$query['p_id']; // Fetching URL Parameter
	
	
  /*  

	,hospital.Name,hospital.Address,hospital.ContactNo                                                                                 */

	$sql = "SELECT
labs.LabName,
labs.LabID,
labs.Type,
labs.Address,
reports.Date
FROM
labs
INNER JOIN reports ON reports.LabID = labs.LabID  where reports.PatientID='$p_id' group by labs.LabID having count(*) >0";
	//$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	
	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('LabName'=>$row[0],
'LabID'=>$row[1],
'Type'=>$row[2],
'Address'=>$row[3],
'reportDate'=>$row[4]
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>