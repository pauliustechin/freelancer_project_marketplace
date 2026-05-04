import useProjectsStore from "../store/projectsStore";
import { Link } from "react-router";

const ProjectsPage = () => {

  const { projects } = useProjectsStore(state => state);

  return (
    <>
      <div>ProjectsPage</div>
      <div><Link to="/"><button className='border text-xl font-bold'>Go to HomePage</button></Link></div>
      <div>{projects.map(project => <p key={project.projectId}>{project.projectName}</p>)}</div>
    </>
  )
   
}

export default ProjectsPage