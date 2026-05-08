import InputField from "../../components/shared/InputField";
import { Link } from "react-router";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
import useUsersStore from "../../store/usersStore";
import { CiUser } from "react-icons/ci";
import { TbLockPassword } from "react-icons/tb";
import { MdAlternateEmail } from "react-icons/md";
import { TiUserOutline } from "react-icons/ti";

const RegisterPage = () => {
  const navigate = useNavigate();
  const { registerNewAccount } = useUsersStore((state) => state);

  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm();

  const onSubmit = (formData) => {
    registerNewAccount(formData, navigate);
    reset();
  };

  return (
    <>
      <main className="flex min-h-screen">
        <div className="w-[50%] bg-cyan-600 text-start p-15 pr-60 text-white flex flex-col gap-8">
          <Link to="/" className="text-3xl font-bold mb-10">
            <h1>[logo]FreelancerMarketplace</h1>
          </Link>
          <h1 className="text-4xl font-bold">
            Designed for full Freelance Support
          </h1>
          <p>
            Manage your projects, track bids and grow your business from
            anywhere!
          </p>
        </div>
        <div className="bg-slate-900 w-[50%] text-white flex flex-col items-start p-25 gap-4">
          <h1 className="text-4xl font-bold">Register an account</h1>
          <p className="text-gray-400">Please enter your details.</p>
          <form
            onSubmit={handleSubmit(onSubmit)}
            className="w-full flex flex-col items-start gap-4 "
          >
            <InputField
              register={register}
              label={<CiUser />}
              id="username"
              type="text"
              placeholder="username"
              required
              min={6}
              errors={errors}
            />

            <InputField
              register={register}
              id="firstName"
              type="firstName"
              placeholder="firstName"
              label={<TiUserOutline />}
              required
              min={3}
              errors={errors}
            />

            <InputField
              register={register}
              id="lastName"
              type="lastName"
              placeholder="lastName"
              label={<TiUserOutline />}
              required
              min={3}
              errors={errors}
            />

            <InputField
              register={register}
              id="email"
              type="email"
              placeholder="email"
              label={<MdAlternateEmail />}
              required
              errors={errors}
            />

            <InputField
              register={register}
              id="password"
              type="password"
              placeholder="password"
              label={<TbLockPassword />}
              required
              min={8}
              errors={errors}
            />

            <div className="flex gap-4 w-full">
              <label className="flex gap-2">
                <input type="radio" value="ROLE_SELLER" {...register("role")} />
                Freelancer
              </label>

              <label className="flex gap-2">
                <input type="radio" value="ROLE_CLIENT" {...register("role")} />
                Company
              </label>
            </div>

            <button
              type="submit"
              className="btn btn-primary bg-cyan-600 border-none w-[50%]"
            >
              Sign up
            </button>
            <p className="text-gray-400">
              Already have an account?
              <Link to="/login">
                <span className="underline ml-2 text-cyan-500 font-bold">
                  Log in
                </span>
              </Link>
            </p>
          </form>
        </div>
      </main>
    </>
  );
};

export default RegisterPage;
