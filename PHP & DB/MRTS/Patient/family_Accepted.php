<?php

include '../db.php';
 
   $url=basename($_SERVER['REQUEST_URI']);
$parts=parse_url($url);
 	parse_str($parts['query'], $query);
	
	$p_id=$query['p_id']; // Fetching URL Parameter
	
	
  /*  

	,hospital.Name,hospital.Address,hospital.ContactNo                                                                                 */

	$sql = "SELECT
familymember.PrimaryID,
familymember.Type,
familymember.MemberID,
familymember.Type,
users.LastName,
users.FirstName,
users.MiddleName,
users.Address,
users.ContactNo,
users.Type,
users.Gender,
users.DOB,
users.BloodGrp,
users.Email
FROM
familymember
INNER JOIN users ON familymember.MemberID = users.ID where familymember.PrimaryID='$p_id' and familymember.Accepted='Accepted'";

	//$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	
	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('Relation_Type'=>$row[1],
'MemberID'=>$row[2],
'm_Last_name'=>$row[4],
'm_first_name'=>$row[5],
'm_mid_name'=>$row[6],
'm_Address'=>$row[7],
'm_ContactNo'=>$row[8],
'users_Type'=>$row[9],
'm_Gender'=>$row[10],

'm_DOB'=>$row[11],
'm_BloodGrp'=>$row[12],
'm_Email'=>$row[13]
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>