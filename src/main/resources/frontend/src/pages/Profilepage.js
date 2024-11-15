// // src/pages/ProfilePage.js
// import React, { useState } from 'react';
// // import ProfileDetails from '../components/profiledetails';

// function ProfilePage() {
//   const [isAuthenticated, setIsAuthenticated] = useState(false);
//   const [user, setUser] = useState(null);

//   const handleAuthSuccess = (userData) => {
//     setUser(userData);  
//     setIsAuthenticated(true); 
//   };

//   return (
//     <div>
//       {!isAuthenticated ? (
//         <AuthForm onAuthSuccess={handleAuthSuccess} />
//       ) : (
//         <ProfileDetails 
//           fullName={user.fullName} 
//           email={user.email} 
//           password={user.password} 
//         />
//       )}

      
//     </div>
//   );
// }

// export default ProfilePage;

import React from "react";

function Profilepage (){
  return (
    <div>
      <h1>Welcome to Your Profile</h1>
      <p>This is the profile page.</p>
    </div>
  );
};

export default Profilepage;
