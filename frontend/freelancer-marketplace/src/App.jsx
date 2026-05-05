import HomePage from "./pages/public/HomePage";
import LoginPage from "./pages/auth/LoginPage";
import { Routes, Route } from "react-router";
import ProjectsPage from "./pages/public/ProjectsPage";
import RegisterPage from "./pages/auth/RegisterPage";
import Header from "./components/shared/Header";
import Footer from "./components/shared/Footer";
import PrivateRoute from "./components/shared/PrivateRoute";
import ClientPage from "./pages/private/ClientPage";
import FreelancerPage from "./pages/private/FreelancerPage";
import AdminPage from "./pages/private/AdminPage";
import AuthProvider from "./store/AuthProvider";

import "./App.css";

function App() {

  return (
    <>
      <AuthProvider>
        <Header />
        <Routes>
          <Route index element={<HomePage />} />
          <Route path="/projects" element={<ProjectsPage />} />

          <Route path="/" element={<PrivateRoute publicPage />}>
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
          </Route>

          <Route path="/" element={<PrivateRoute clientOnly />}>
            <Route path="/client" element={<ClientPage />} />
          </Route>

          <Route path="/" element={<PrivateRoute freelancerOnly />}>
            <Route path="/freelancer" element={<FreelancerPage />} />
          </Route>

          <Route path="/" element={<PrivateRoute adminOnly />}>
            <Route path="/admin" element={<AdminPage />} />
          </Route>
        </Routes>
        <Footer />
      </AuthProvider>
    </>
  );
}

export default App;
