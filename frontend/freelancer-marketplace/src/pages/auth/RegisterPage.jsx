import InputField from "../../components/shared/InputField";
import { Link } from "react-router";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
import useUsersStore from "../../store/usersStore";

const RegisterPage = () => {

  const navigate = useNavigate();
  const { registerNewAccount } = useUsersStore(state => state);

  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm();


  const onSubmit = (formData) => {
    registerNewAccount(formData, navigate);
    reset();
  }

    return (
    <>
      <div>LoginPage</div>
      <div><Link to="/"><button className='border text-xl font-bold'>Go to HomePage</button></Link></div>
      <form onSubmit={handleSubmit(onSubmit)} className=" w-68 border">
        <h2 className="text-2xl font-bold">Register an account</h2>

        <InputField 
          register={register}
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
          required
          min={3}
          errors={errors}
        />

        <InputField 
          register={register}
          id="lastName"
          type="lastName"
          placeholder="lastName"
          required
          min={3}
          errors={errors}
        />

        <InputField 
          register={register}
          id="email"
          type="email"
          placeholder="email"
          required
          errors={errors}
        />

        <InputField 
          register={register}
          id="password"
          type="password"
          placeholder="password"
          required
          min={8}
          errors={errors}
        />

        <div className="flex gap-4 w-full justify-center">
          <label className="flex gap-2">
            <input 
              type="radio" 
              value="ROLE_SELLER" 
              {...register("role")} 
            />
            Freelancer
          </label>

          <label className="flex gap-2">
            <input 
              type="radio" 
              value="ROLE_CLIENT" 
              {...register("role")} 
            />
            Company
          </label>
        </div>

        <button type="submit" className="my-button m-2">Submit</button>
        <p>Already have an account? <Link to="/login"><span className="underline">Log in</span></Link></p>

      </form>

    </>

  )
}

export default RegisterPage