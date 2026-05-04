// import { useEffect } from "react";
// import useProjectsStore from "./store/projectsStore";
import HomePage from "./pages/HomePage";
import LoginPage from "./pages/auth/LoginPage";
import { Routes, Route } from "react-router";
import ProjectsPage from "./pages/ProjectsPage";
import RegisterPage from "./pages/auth/RegisterPage";
import Header from "./shared/Header";
import Footer from "./shared/Footer";

import "./App.css";

function App() {
  // const { searchForProjects } = useProjectsStore((state) => state);

  // useEffect(() => {
  //   searchForProjects();
  // }, [searchForProjects]);

  return (
    <>
      <Header />
        <Routes>
          <Route index element={<HomePage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/projects" element={<ProjectsPage />} />
        </Routes>
      <Footer />
    </>
  );
}

export default App;
