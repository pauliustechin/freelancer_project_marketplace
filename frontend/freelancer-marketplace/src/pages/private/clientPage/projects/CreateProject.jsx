import InputField from "../../../../components/shared/InputField";
import { useForm, Controller } from "react-hook-form";
import MyPdfDropzone from "../../../../components/shared/MyPdfDropzone";
import MyDatePicker from "../../../../components/shared/MyDatePicker";

const CreateProject = () => {
  const {
    register,
    handleSubmit,
    watch,
    control,
    formState: { errors },
  } = useForm();

  const startDate = watch("startDate");

  const onSubmit = (formData) => {
    console.log(formData);
  };

  return (
    <main className="text-slate-200 bg-slate-400 min-h-screen p-8">
      <h1 className="text-2xl font-bold text-start mb-2">Create New Project</h1>
      <div className="bg-slate-700 p-8 rounded-xl">
        <form
          onSubmit={handleSubmit(onSubmit)}
          className="w-full flex flex-col gap-4 "
        >
          <div className="flex flex-col text-start">
            <label htmlFor="projectName" className="text-slate-300">
              Project Title
            </label>
            <InputField
              register={register}
              id="projectName"
              type="text"
              placeholder="e.g. E-commerce Website Redesign"
              required
              min={8}
              errors={errors}
            />
          </div>

          <div className="flex flex-col text-start">
            <label htmlFor="projectName" className="text-slate-300">
              Project Desciption
            </label>
            <textarea
              id="description"
              className="w-full rounded-lg p-2 bg-slate-800 text-sm h-20 m-1"
              placeholder="Describe your project in detail..."
              {...register("description", {
                required: "Description is required",
                minLength: {
                  value: 10,
                  message: "Minimum 10 characters required",
                },
              })}
            />
            {errors?.description && (
              <p className="my-error">{errors?.description.message}</p>
            )}
          </div>

          <div>
            <div className="flex text-start">
              <div>
                <label htmlFor="projectName" className="text-slate-300">
                  Start Date
                </label>
                <Controller
                  name="startDate"
                  control={control}
                  rules={{ required: "Start date is required" }}
                  render={({ field }) => (
                    <MyDatePicker
                      theme={"bg-slate-800 text-white"}
                      value={field.value}
                      onChange={field.onChange}
                      minDate={new Date()}
                      placeholder="Select start date"
                    />
                  )}
                />
              </div>

              <div>
                <label htmlFor="projectName" className="text-slate-300">
                  End Date (Optional)
                </label>
                <Controller
                  name="endDate"
                  control={control}
                  render={({ field }) => (
                    <MyDatePicker
                      theme={"bg-slate-800 text-white"}
                      value={field.value}
                      onChange={field.onChange}
                      placeholder="Select end date "
                      disabled={!startDate}
                      minDate={startDate || new Date()}
                    />
                  )}
                />
              </div>
            </div>
            {errors?.startDate && (
              <p className="my-error text-start">{errors?.startDate.message}</p>
            )}
          </div>

          <div className="flex flex-col text-start">
            <label htmlFor="projectName" className="text-slate-300">
              Project files (Optional)
            </label>
            <MyPdfDropzone></MyPdfDropzone>
          </div>

          <button
            type="submit"
            className="btn btn-primary bg-cyan-600 border-none w-fit self-end"
          >
            Create Project
          </button>
        </form>
      </div>
    </main>
  );
};

export default CreateProject;
