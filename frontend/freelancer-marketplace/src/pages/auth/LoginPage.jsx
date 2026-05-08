import { Link } from "react-router";
import { useForm } from "react-hook-form";
import InputField from "../../components/shared/InputField";
import { useNavigate } from "react-router";
import useUsersStore from "../../store/usersStore";
import { CiUser } from "react-icons/ci";
import { TbLockPassword } from "react-icons/tb";

const LoginPage = () => {
  const navigate = useNavigate();
  const { loginUser } = useUsersStore((state) => state);

  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm();

  const onSubmit = (formData) => {
    loginUser(formData, navigate);
    reset();
  };

  return (
    <main className="flex min-h-screen">
      <div className="w-[50%] bg-cyan-600 text-start p-15 pr-60 text-white flex flex-col gap-8">
        <Link to="/" className="text-3xl font-bold mb-10">
          <h1>[logo]FreelancerMarketplace</h1>
        </Link>
        <h1 className="text-4xl font-bold">
          Designed for full Freelance Support
        </h1>
        <p>
          Manage your projects, track bids and grow your business from anywhere!
        </p>
      </div>
      <div className="bg-slate-900 w-[50%] text-white flex flex-col items-start p-25 gap-4">
        <h1 className="text-4xl font-bold">Log in</h1>
        <p className="text-gray-400">
          Welcome back! Please enter your details.
        </p>
        <form
          onSubmit={handleSubmit(onSubmit)}
          className="w-[70%] flex flex-col gap-4"
        >
          <InputField
            register={register}
            label={<CiUser />}
            id="username"
            type="text"
            placeholder="username"
            required
            min={3}
            errors={errors}
          />

          <InputField
            register={register}
            id="password"
            type="password"
            label={<TbLockPassword />}
            placeholder="password"
            required
            min={8}
            errors={errors}
          />

          <button
            type="submit"
            className="btn btn-primary bg-cyan-600 border-none w-[80%] self-center"
          >
            Sign in
          </button>
          <p className="text-gray-400">
            Dont have an account?
            <Link to="/register">
              <span className="underline ml-2 text-cyan-500 font-bold">
                Sign Up
              </span>
            </Link>
          </p>
        </form>
      </div>
    </main>
  );
};

export default LoginPage;
