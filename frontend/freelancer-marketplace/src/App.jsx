import { useEffect } from 'react';
import useProjectsStore from './store/projectsStore';
import HomePage from './pages/HomePage';
import LoginPage from './pages/auth/LoginPage';
import { Routes, Route } from "react-router";
import ProjectsPage from './pages/ProjectsPage';

import './App.css'

function App() {

  const { searchForProjects } = useProjectsStore(state => state);

  useEffect(() => {
    searchForProjects();
  }, [searchForProjects]);

  return (
    <>
      <Routes>
        <Route index element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/projects" element={<ProjectsPage />} />
      </Routes>
    </>
  )
}

export default App
