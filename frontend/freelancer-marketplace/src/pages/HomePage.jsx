import useProjectsStore from '../store/projectsStore';

const HomePage = () => {

const { projects } = useProjectsStore(state => state);

  return (
    <>
    <p>HOME PAGE</p>
    <div>{projects.map(project => <p key={project.projectId}>{project.projectName}</p>)}</div>
    </>
    
  )
}

export default HomePage