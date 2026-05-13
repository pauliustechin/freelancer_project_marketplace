import HomePage from "./pages/public/HomePage";
import LoginPage from "./pages/auth/LoginPage";
import { Routes, Route } from "react-router";
import ProjectsPage from "./pages/public/projects/ProjectsPage";
import RegisterPage from "./pages/auth/RegisterPage";
import PrivateRoute from "./components/shared/PrivateRoute";
import ClientPage from "./pages/private/clientPage/ClientPage";
import FreelancerPage from "./pages/private/freelancerPage/FreelancerPage";
import AdminPage from "./pages/private/AdminPage";
import AuthProvider from "./pages/auth/AuthProvider";
import ProjectInfo from "./pages/public/projects/ProjectInfo";
import ProjectBidsTable from "./pages/private/clientPage/projectBids/ProjectBidsTable";
import ClientDashboard from "./pages/private/clientPage/dashboard/ClientDashboard";
import CreateProject from "./pages/private/clientPage/projects/CreateProject";
import FreelancerDashboard from "./pages/private/freelancerPage/freelancerDashboard/FreelancerDashboard";
import EditProject from "./pages/private/clientPage/projects/EditProject";
import ContractsTable from "./pages/private/shared/ContractsTable";
import "./App.css";

function App() {

  return (
    <>
      <AuthProvider>
        <Routes>
          <Route index element={<HomePage />} />

          <Route path="/projects" element={<ProjectsPage />}>
            <Route path=":projectId" element={<ProjectInfo />} />
          </Route>

          <Route path="/" element={<PrivateRoute publicPage />}>
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
          </Route>

          <Route path="/" element={<PrivateRoute clientOnly />}>
            <Route path="/client" element={<ClientPage />}>
              <Route path="" element={<ClientDashboard/>}/>
              <Route path="projects/:projectId" element={<ProjectBidsTable />}/>
              <Route path="create-project" element={<CreateProject />}/>
              <Route path="edit-project/:projectId" element={<EditProject />}/>
              <Route path="contracts" element={<ContractsTable />}/>
            </Route>
          </Route>

          <Route path="/" element={<PrivateRoute freelancerOnly />}>
            <Route path="/freelancer" element={<FreelancerPage />} >
              <Route path="" element={<FreelancerDashboard/>}/>
              <Route path="contracts" element={<ContractsTable />}/>
            </Route>
          </Route>

          <Route path="/" element={<PrivateRoute adminOnly />}>
            <Route path="/admin" element={<AdminPage />} />
          </Route>
        </Routes>
      </AuthProvider>
    </>
  );
}

export default App;
