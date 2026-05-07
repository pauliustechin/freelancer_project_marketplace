import useProjectsStore from "../../../store/projectsStore";
import { Link } from "react-router";
import MyPagination from "../../../components/shared/MyPagination";
import MyFiltering from "../../../components/shared/MyFiltering";


const ProjectsPage = () => {

  const { projects } = useProjectsStore(state => state);

  return (
    <main>
      <div>ProjectsPage</div>
      <MyFiltering></MyFiltering>
      <div><Link to="/"><button className='border text-xl font-bold'>Go to HomePage</button></Link></div>
      <div>{projects.map(project => <p key={project.projectId}>{project.projectName}</p>)}</div>
      <MyPagination></MyPagination>
    </main>
  )
}

export default ProjectsPage