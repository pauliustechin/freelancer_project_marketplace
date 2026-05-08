import useProjectsStore from "../../store/projectsStore";
import ProjectCard from "./projects/ProjectCard";
import { FaArrowRight } from "react-icons/fa";
import office from "../../assets/office.jpg";
import Header from "../../components/shared/Header";
import Footer from "../../components/shared/Footer";

const HomePage = () => {
  const { projects } = useProjectsStore((state) => state);

  return (
    <>
      <Header />
      <main className="flex flex-col items-center p-8">
        <section className="w-[50%] m-10 flex flex-col gap-4">
          <h1 className="text-4xl font-bold">
            Accelerate your projects with top talent
          </h1>
          <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Amet
            deleniti perspiciatis nulla aut itaque voluptatum est soluta beatae
            accusantium, voluptatibus, autem voluptatem rerum, rem consequuntur
            velit nemo reprehenderit asperiores illo.
          </p>
          <div className="flex gap-4 w-full justify-center">
            <button className="btn primary bg-cyan-400">
              Browse projects
              <FaArrowRight />
            </button>
            <button className="btn primary">Hire Talent</button>
          </div>
        </section>

        <img src={office} alt="office" className="w-full rounded-2xl mb-10 h-100 object-cover" />

        <div className="flex gap-2 justify-between w-full mb-10">
          <div
            div
            className="w-50 max-w-sm rounded-2xl bg-white p-6 shadow-xl border border-gray-100"
          >
            <div className="flex items-center justify-between h-40">
              <div>
                <p className="text-sm font-medium text-gray-500">
                  Active Freelancers
                </p>
                <h2 className="mt-2 text-4xl font-bold text-gray-900">300K</h2>
              </div>
            </div>
          </div>

          <div
            div
            className="w-50 max-w-sm rounded-2xl bg-slate-700 p-6 shadow-xl border border-gray-100"
          >
            <div className="flex items-center justify-between h-40">
              <div>
                <p className="text-sm font-medium text-gray-200">Categories</p>
                <h2 className="mt-2 text-4xl font-bold text-gray-200">15</h2>
              </div>
            </div>
          </div>

          <div
            div
            className="w-50 max-w-sm rounded-2xl bg-gray-300 p-6 shadow-xl border border-gray-100"
          >
            <div className="flex items-center justify-between h-40">
              <div>
                <p className="text-sm font-medium text-gray-500">
                  Projects completed
                </p>
                <h2 className="mt-2 text-4xl font-bold text-gray-900">300K</h2>
              </div>
            </div>
          </div>

          <div
            div
            className="w-50 max-w-sm rounded-2xl bg-cyan-400 p-6 shadow-xl border border-gray-100"
          >
            <div className="flex items-center justify-between h-40">
              <div>
                <p className="text-sm font-medium text-gray-500">
                  Avg bids per project
                </p>
                <h2 className="mt-2 text-4xl font-bold text-gray-900">12</h2>
              </div>
            </div>
          </div>
        </div>

        <section className="flex flex-col gap-4 p-4 text-start">
          <h1 className="text-3xl font-bold">Featured Opportunities</h1>
          <div className="flex justify-between">
            <p className="w-[60%]">
              Lorem ipsum dolor sit, amet consectetur adipisicing elit. Minus
              voluptates ipsa deserunt possimus, vitae error rem quo, aliquam
              officiis reiciendis, quam laborum dicta repudiandae consequuntur
              doloribus at facere quisquam odio.
            </p>
            <button className="btn primary bg-white">View all projects</button>
          </div>

          <div className="flex h-80 gap-4 justify-between">
            {projects.slice(0, 3).map((project) => (
              <ProjectCard
                key={project.projectId}
                project={project}
                styling="w-80"
              ></ProjectCard>
            ))}
          </div>
        </section>

        <section className="bg-stone-600 w-full rounded-lg text-olive-100">
          <div className="w-[70%] text-start p-8">
            <p className="font-bold mb-4">FREELANCER TESTIMONIAL</p>
            <p className="text-2xl">
              "Lorem ipsum, dolor sit amet consectetur adipisicing elit. Dolor
              nam odit ea veritatis molestias, porro quam non consequatur!
              Quidem accusamus deserunt vitae quae cumque nulla alias tempora
              pariatur. Alias, hic?"
            </p>
            <p className="font-bold mt-4">John Doe</p>
            <p>Top rated developer</p>
          </div>
        </section>
        <div></div>
      </main>
      <Footer />
    </>
  );
};

export default HomePage;
