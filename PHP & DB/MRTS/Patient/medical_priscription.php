<?php

$host="localhost";
$uname="root";
$pass="down";
$db="mrtnfc";

$conn = mysqli_connect($host, $uname, $pass,$db);


if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
else
{
	//echo "Success";
}
 
   $url=basename($_SERVER['REQUEST_URI']);
$parts=parse_url($url);
 	parse_str($parts['query'], $query);
	
	$p_id=$query['p_id']; // Fetching URL Parameter
	
	
 
	$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	 /*  

	,hospital.Name,hospital.Address,hospital.ContactNo                                                                                 */

	$sql = "SELECT
prescription.DoctorID,
prescription.PrescriptionID,
prescription.Pic,
prescription.Date,
users.LastName,
users.FirstName,
users.MiddleName
FROM
users
INNER JOIN prescription ON users.ID = prescription.DoctorID where prescription.PatientID='$p_id'";
	//$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	
	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('DoctorID'=>$row[0],
'PrescriptionID'=>$row[1],
'PrescPicture'=>$row[2],
'Date'=>$row[3],
'D_Last_name'=>$row[4],
'D_first_name'=>$row[5],

'D_mid_name'=>$row[6]
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>