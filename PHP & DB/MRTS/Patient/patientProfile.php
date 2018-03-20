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
users.Address,


users.Gender,
users.DOB,
users.BloodGrp,
users.UserName,

users.RegTimestamp,
users.Email,
users.AadharNo,
users.State,
users.City,
patient.Allergies,
users.ContactNo
FROM
users
INNER JOIN patient ON users.ID = patient.ID WHERE users.ID='$p_id'";
	//$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	
	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('patientID'=>$row[0],
'LastName'=>$row[1],
'FirstName'=>$row[2],
'MiddleName'=>$row[3],
'Address'=>$row[4],
'Gender'=>$row[5],
'DOB'=>$row[6],
'BloodGrp'=>$row[7],

'RegTimestamp'=>$row[9],
'Email'=>$row[10],

'AadharNo'=>$row[11],
'State'=>$row[12],
'City'=>$row[13],
'Allergies'=>$row[14],
'ContactNo'=>$row[15]
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>