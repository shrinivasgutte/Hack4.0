<?php
include '../db.php';
  // $url=basename($_SERVER['REQUEST_URI']);
//$parts=parse_url($url);
 //	parse_str($parts['query'], $query);
	
//	$p_id=$query['p_id']; // Fetching URL Parameter
	
	
 
 /*  

	,hospital.Name,hospital.Address,hospital.ContactNo                                                                                 */

	$sql = "SELECT
doctor.DoctorID,
doctor.Qualification,
doctor.Speciality,
doctor.HospitalID,
hospital.Name,
hospital.ContactNo,
hospital.Address,
hospital.EmergencyNo,
hospital.WorkingHours,

users.LastName,
users.FirstName,
users.MiddleName,

users.ContactNo,
users.Gender,
users.Email,
users.Address
FROM
users
INNER JOIN doctor ON users.ID = doctor.DoctorID
INNER JOIN hospital ON doctor.HospitalID = hospital.HospitalID
WHERE
users.Type = 'Doctor'";//he all dr chayy
	//$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	
	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('DoctorID'=>$row[0],
'Qualification'=>$row[1],
'Speciality'=>$row[2],
'HospitalID'=>$row[3],
'HospitalName'=>$row[4],
'H_ContactNo'=>$row[5],
'H_Address'=>$row[6],

'H_EmergencyNo'=>$row[7],
'H_WorkingHours'=>$row[8],
'D_Last_name'=>$row[9],
'D_first_name'=>$row[10],
'D_mid_name'=>$row[11],

'D_ContactNo'=>$row[12],
'D_Gender'=>$row[13],
'D_Email'=>$row[14],
'dr_Address'=>$row[15]


));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>