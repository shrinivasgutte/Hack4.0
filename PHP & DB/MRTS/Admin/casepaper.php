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
casepaper.Description,
casepaper.Type,
casepaper.DoctorID,
casepaper.HospitalID,
casepaper.Pic,
casepaper.Date,

users.LastName,
users.FirstName,
users.MiddleName,
users.Email,

doc.LastName,
doc.FirstName,
doc.MiddleName,

hospital.`Name`
FROM
casepaper
INNER JOIN users AS doc ON casepaper.DoctorID = doc.ID
INNER JOIN users ON users.ID = casepaper.PatientID
INNER JOIN hospital ON hospital.HospitalID = casepaper.HospitalID AND casepaper.HospitalID = hospital.HospitalID where casepaper.PatientID='$p_id'";
	//$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	
	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('Description'=>$row[0],
'Type'=>$row[1],
'DoctorID'=>$row[2],
'HosID'=>$row[3],
'Pic'=>$row[4],
'Date'=>$row[5],

'P_last_name'=>$row[6],
'P_mid_name'=>$row[7],
'P_first_name'=>$row[8],
'P_email'=>$row[9],

'D_Last_name'=>$row[10],
'D_first_name'=>$row[11],
'D_mid_name'=>$row[12],
'Hos_Name'=>$row[13]
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>